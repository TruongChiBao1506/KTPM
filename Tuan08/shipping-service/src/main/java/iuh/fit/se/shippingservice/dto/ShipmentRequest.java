package iuh.fit.se.shippingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {
    private String orderId; // Mã đơn hàng
    private String address; // Địa chỉ giao hàng
    private String city; // Thành phố
    private String state; // Tỉnh/Tiểu bang
    private String zipCode; // Mã bưu chính
    private String country; // Quốc gia
}
