package iuh.fit.se.inventoryservice.controller;

import iuh.fit.se.inventoryservice.dto.InventoryResponse;
import iuh.fit.se.inventoryservice.dto.StockUpdateRequest;
import iuh.fit.se.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    // API lấy thông tin tồn kho theo mã sản phẩm
    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable String productId) {
        InventoryResponse inventory = inventoryService.getInventoryByProductId(productId);
        return ResponseEntity.ok(inventory);
    }

    // API lấy thông tin tồn kho theo danh sách mã sản phẩm
    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getInventoryForProducts(@RequestParam List<String> productIds) {
        List<InventoryResponse> inventoryList = inventoryService.getInventoryForProducts(productIds);
        return ResponseEntity.ok(inventoryList);
    }

    // API thêm số lượng vào tồn kho
    @PostMapping("/add")
    public ResponseEntity<InventoryResponse> addStock(@RequestBody StockUpdateRequest request) {
        InventoryResponse inventory = inventoryService.addStock(request);
        return new ResponseEntity<>(inventory, HttpStatus.CREATED);
    }

    // API đặt trước số lượng sản phẩm
    @PostMapping("/reserve")
    public ResponseEntity<InventoryResponse> reserveStock(@RequestBody StockUpdateRequest request) {
        InventoryResponse inventory = inventoryService.reserveStock(request);
        return ResponseEntity.ok(inventory);
    }

    // API xác nhận giảm số lượng tồn kho
    @PostMapping("/confirm")
    public ResponseEntity<InventoryResponse> confirmStockReduction(@RequestBody StockUpdateRequest request) {
        InventoryResponse inventory = inventoryService.confirmStockReduction(request);
        return ResponseEntity.ok(inventory);
    }

    // API hủy đặt trước số lượng sản phẩm
    @PostMapping("/cancel")
    public ResponseEntity<InventoryResponse> cancelReservation(@RequestBody StockUpdateRequest request) {
        InventoryResponse inventory = inventoryService.cancelReservation(request);
        return ResponseEntity.ok(inventory);
    }
}
