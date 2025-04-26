package iuh.fit.se.shippingservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId; // Mã đơn hàng

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status; // Trạng thái vận chuyển

    private String trackingNumber; // Mã theo dõi đơn hàng

    private String address; // Địa chỉ giao hàng

    private String city; // Thành phố

    private String state; // Tỉnh/Tiểu bang

    private String zipCode; // Mã bưu chính

    private String country; // Quốc gia

    private String carrierName; // Tên đơn vị vận chuyển

    private LocalDateTime shippedAt; // Thời gian gửi hàng

    private LocalDateTime estimatedDelivery; // Thời gian dự kiến giao hàng

    private LocalDateTime deliveredAt; // Thời gian đã giao hàng

    private LocalDateTime createdAt; // Thời gian tạo

    private LocalDateTime updatedAt; // Thời gian cập nhật

    // Enum định nghĩa các trạng thái vận chuyển
    public enum ShipmentStatus {
        PENDING, // Đang chờ xử lý
        PROCESSING, // Đang xử lý
        SHIPPED, // Đã gửi hàng
        IN_TRANSIT, // Đang vận chuyển
        DELIVERED, // Đã giao hàng
        FAILED // Giao hàng thất bại
    }

    // Tự động cập nhật thời gian tạo khi lưu entity
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Tự động cập nhật thời gian khi cập nhật entity
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
