package iuh.fit.se.shippingservice.dto;

import iuh.fit.se.shippingservice.entities.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {
    private Long id;
    private String orderId;
    private Shipment.ShipmentStatus status;
    private String trackingNumber;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String carrierName;
    private LocalDateTime shippedAt;
    private LocalDateTime estimatedDelivery;
    private LocalDateTime deliveredAt;

    // Phương thức chuyển đổi từ Entity sang DTO
    public static ShipmentResponse fromEntity(Shipment shipment) {
        ShipmentResponse response = new ShipmentResponse();
        response.setId(shipment.getId());
        response.setOrderId(shipment.getOrderId());
        response.setStatus(shipment.getStatus());
        response.setTrackingNumber(shipment.getTrackingNumber());
        response.setAddress(shipment.getAddress());
        response.setCity(shipment.getCity());
        response.setState(shipment.getState());
        response.setZipCode(shipment.getZipCode());
        response.setCountry(shipment.getCountry());
        response.setCarrierName(shipment.getCarrierName());
        response.setShippedAt(shipment.getShippedAt());
        response.setEstimatedDelivery(shipment.getEstimatedDelivery());
        response.setDeliveredAt(shipment.getDeliveredAt());
        return response;
    }
}
