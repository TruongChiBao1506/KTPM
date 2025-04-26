package iuh.fit.se.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateRequest {
    private String productId; // Mã sản phẩm
    private Integer quantity; // Số lượng cập nhật
}
