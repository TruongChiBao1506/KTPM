package iuh.fit.se.shippingservice.service;

import iuh.fit.se.shippingservice.dto.ShipmentRequest;
import iuh.fit.se.shippingservice.dto.ShipmentResponse;
import iuh.fit.se.shippingservice.dto.ShipmentStatusUpdateRequest;

import java.util.List;

public interface ShippingService {
    public ShipmentResponse createShipment(ShipmentRequest request);
    public ShipmentResponse updateShipmentStatus(ShipmentStatusUpdateRequest request);
    public ShipmentResponse getShipmentByOrderId(String orderId);
    public ShipmentResponse getShipmentByTrackingNumber(String trackingNumber);
    public List<ShipmentResponse> getAllShipments();

}
