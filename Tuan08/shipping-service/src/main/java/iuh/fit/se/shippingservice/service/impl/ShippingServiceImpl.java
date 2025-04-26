package iuh.fit.se.shippingservice.service.impl;

import iuh.fit.se.shippingservice.dto.ShipmentRequest;
import iuh.fit.se.shippingservice.dto.ShipmentResponse;
import iuh.fit.se.shippingservice.dto.ShipmentStatusUpdateRequest;
import iuh.fit.se.shippingservice.entities.Shipment;
import iuh.fit.se.shippingservice.repositories.ShipmentRepository;
import iuh.fit.se.shippingservice.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    // Tạo đơn vận chuyển mới
    @Override
    @Transactional
    public ShipmentResponse createShipment(ShipmentRequest request) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("shipping");

        return circuitBreaker.run(() -> {
            // Kiểm tra đơn vận chuyển đã tồn tại cho đơn hàng này chưa
            if (shipmentRepository.findByOrderId(request.getOrderId()).isPresent()) {
                throw new RuntimeException("Đơn vận chuyển đã tồn tại cho đơn hàng: " + request.getOrderId());
            }

            Shipment shipment = new Shipment();
            shipment.setOrderId(request.getOrderId());
            shipment.setStatus(Shipment.ShipmentStatus.PENDING);
            shipment.setTrackingNumber(generateTrackingNumber());
            shipment.setAddress(request.getAddress());
            shipment.setCity(request.getCity());
            shipment.setState(request.getState());
            shipment.setZipCode(request.getZipCode());
            shipment.setCountry(request.getCountry());
            shipment.setCarrierName("Đơn vị vận chuyển mặc định"); // Có thể động dựa trên quốc gia, v.v.
            shipment.setEstimatedDelivery(LocalDateTime.now().plusDays(5)); // Tính toán dựa trên khoảng cách

            Shipment savedShipment = shipmentRepository.save(shipment);
            return ShipmentResponse.fromEntity(savedShipment);
        }, throwable -> {
            // Xử lý fallback khi có lỗi
            ShipmentResponse fallbackResponse = new ShipmentResponse();
            fallbackResponse.setOrderId(request.getOrderId());
            fallbackResponse.setStatus(Shipment.ShipmentStatus.FAILED);
            return fallbackResponse;
        });
    }

    // Cập nhật trạng thái đơn vận chuyển
    @Override
    @Transactional
    public ShipmentResponse updateShipmentStatus(ShipmentStatusUpdateRequest request) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("shipping");

        return circuitBreaker.run(() -> {
            Shipment shipment = shipmentRepository.findByOrderId(request.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn vận chuyển cho đơn hàng: " + request.getOrderId()));

            shipment.setStatus(request.getStatus());

            // Cập nhật thời gian dựa trên trạng thái
            if (request.getStatus() == Shipment.ShipmentStatus.SHIPPED) {
                shipment.setShippedAt(LocalDateTime.now());
            } else if (request.getStatus() == Shipment.ShipmentStatus.DELIVERED) {
                shipment.setDeliveredAt(LocalDateTime.now());
            }

            Shipment updatedShipment = shipmentRepository.save(shipment);
            return ShipmentResponse.fromEntity(updatedShipment);
        }, throwable -> {
            // Xử lý fallback khi có lỗi
            ShipmentResponse fallbackResponse = new ShipmentResponse();
            fallbackResponse.setOrderId(request.getOrderId());
            return fallbackResponse;
        });
    }

    // Lấy thông tin đơn vận chuyển theo mã đơn hàng
    @Override
    public ShipmentResponse getShipmentByOrderId(String orderId) {
        Shipment shipment = shipmentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn vận chuyển cho đơn hàng: " + orderId));
        return ShipmentResponse.fromEntity(shipment);
    }
    // Lấy thông tin đơn vận chuyển theo mã theo dõi
    @Override
    public ShipmentResponse getShipmentByTrackingNumber(String trackingNumber) {
        Shipment shipment = shipmentRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn vận chuyển cho mã theo dõi: " + trackingNumber));
        return ShipmentResponse.fromEntity(shipment);
    }

    // Lấy tất cả các đơn vận chuyển
    @Override
    public List<ShipmentResponse> getAllShipments() {
        return shipmentRepository.findAll().stream()
                .map(ShipmentResponse::fromEntity)
                .collect(Collectors.toList());
    }
    // Tạo mã theo dõi ngẫu nhiên
    private String generateTrackingNumber() {
        return "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
