package iuh.fit.se.inventoryservice.dto;

import iuh.fit.se.inventoryservice.entities.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    private String productId;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer availableQuantity;
    private boolean isInStock;

    // Phương thức chuyển đổi từ Entity sang DTO
    public static InventoryResponse fromEntity(Inventory inventory) {
        InventoryResponse response = new InventoryResponse();
        response.setProductId(inventory.getProductId());
        response.setQuantity(inventory.getQuantity());
        response.setReservedQuantity(inventory.getReservedQuantity());
        response.setAvailableQuantity(inventory.getAvailableQuantity());
        response.setInStock(inventory.getAvailableQuantity() > 0);
        return response;
    }
}
