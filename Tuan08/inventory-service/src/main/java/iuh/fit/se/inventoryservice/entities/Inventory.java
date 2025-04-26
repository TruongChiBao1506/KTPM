package iuh.fit.se.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String productId; // Mã sản phẩm

    private Integer quantity; // Số lượng tổng

    private Integer reservedQuantity; // Số lượng đã đặt trước

    private LocalDateTime createdAt; // Thời gian tạo

    private LocalDateTime updatedAt; // Thời gian cập nhật

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

    // Trả về số lượng có sẵn (tổng số lượng - số lượng đã đặt trước)
    public Integer getAvailableQuantity() {
        return quantity - reservedQuantity;
    }
}
