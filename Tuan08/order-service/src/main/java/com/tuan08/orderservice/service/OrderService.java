package com.tuan08.orderservice.service;

import com.tuan08.orderservice.event.OrderPlacedEvent;
import com.tuan08.orderservice.model.Order;
import com.tuan08.orderservice.model.OrderItem;
import com.tuan08.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    
    private static final String INVENTORY_TOPIC = "inventory-updates";

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        
        // Ensure orderItems is not null
        if (order.getOrderItems() == null) {
            order.setOrderItems(new ArrayList<>());
        }
        
        // Save the order
        Order savedOrder = orderRepository.save(order);
        
        // Send messages to update inventory
        publishInventoryUpdateEvents(savedOrder);
        
        return savedOrder;
    }

    private void publishInventoryUpdateEvents(Order order) {
        // Null check before iterating
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                OrderPlacedEvent event = new OrderPlacedEvent(
                    order.getId(),
                    item.getProductId(),
                    item.getQuantity()
                );
                
                // Send the event to Kafka
                kafkaTemplate.send(INVENTORY_TOPIC, event);
            }
        }
    }

    public Optional<Order> updateOrderStatus(String id, String status) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setStatus(status);
                    return orderRepository.save(existingOrder);
                });
    }

    public boolean cancelOrder(String id) {
        return orderRepository.findById(id)
                .map(order -> {
                    if ("PENDING".equals(order.getStatus())) {
                        order.setStatus("CANCELLED");
                        orderRepository.save(order);
                        
                        // Send cancellation events to restore inventory
                        if (order.getOrderItems() != null) {
                            for (OrderItem item : order.getOrderItems()) {
                                OrderPlacedEvent event = new OrderPlacedEvent(
                                    order.getId(),
                                    item.getProductId(),
                                    -item.getQuantity() // Negative quantity to restore inventory
                                );
                                
                                // Send the event to Kafka
                                kafkaTemplate.send(INVENTORY_TOPIC, event);
                            }
                        }
                        
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }
}
