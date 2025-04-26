package iuh.fit.se.inventoryservice.service;

import iuh.fit.se.inventoryservice.dto.InventoryResponse;
import iuh.fit.se.inventoryservice.dto.StockUpdateRequest;

import java.util.List;

public interface InventoryService {
    public InventoryResponse getInventoryByProductId(String productId);
    public List<InventoryResponse> getInventoryForProducts(List<String> productIds);
    public InventoryResponse addStock(StockUpdateRequest request);
    public InventoryResponse reserveStock(StockUpdateRequest request);
    public InventoryResponse confirmStockReduction(StockUpdateRequest request);
    public InventoryResponse cancelReservation(StockUpdateRequest request);
}
