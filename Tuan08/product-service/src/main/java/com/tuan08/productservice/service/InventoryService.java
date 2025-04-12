package com.tuan08.productservice.service;

import com.tuan08.productservice.event.OrderPlacedEvent;
import com.tuan08.productservice.model.Product;
import com.tuan08.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    
    private final ProductRepository productRepository;
    
    @KafkaListener(topics = "inventory-updates", groupId = "${spring.kafka.consumer.group-id}")
    public void handleInventoryUpdate(OrderPlacedEvent event) {
        log.info("Received inventory update for product: {}, quantity: {}", 
                event.getProductId(), event.getQuantity());
        
        productRepository.findById(event.getProductId())
                .ifPresent(product -> {
                    // Deduct the quantity (or add back in case of cancellation with negative quantity)
                    int newQuantity = product.getStockQuantity() - event.getQuantity();
                    product.setStockQuantity(newQuantity);
                    productRepository.save(product);
                    
                    log.info("Updated inventory for product: {}, new quantity: {}", 
                            product.getId(), product.getStockQuantity());
                });
    }
}
