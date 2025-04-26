package iuh.fit.se.shippingservice.dto;

import iuh.fit.se.shippingservice.entities.Shipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentStatusUpdateRequest {
    private String orderId; // Mã đơn hàng
    private Shipment.ShipmentStatus status; // Trạng thái vận chuyển mới
}
