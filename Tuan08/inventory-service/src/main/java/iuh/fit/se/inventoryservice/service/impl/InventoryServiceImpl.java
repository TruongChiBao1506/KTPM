package iuh.fit.se.inventoryservice.service.impl;

import iuh.fit.se.inventoryservice.dto.InventoryResponse;
import iuh.fit.se.inventoryservice.dto.StockUpdateRequest;
import iuh.fit.se.inventoryservice.entities.Inventory;
import iuh.fit.se.inventoryservice.repositories.InventoryRepository;
import iuh.fit.se.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    // Lấy thông tin tồn kho theo mã sản phẩm
    @Override
    @Transactional(readOnly = true)
    public InventoryResponse getInventoryByProductId(String productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho sản phẩm: " + productId));
        return InventoryResponse.fromEntity(inventory);
    }

    // Lấy thông tin tồn kho theo danh sách mã sản phẩm
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> getInventoryForProducts(List<String> productIds) {
        return inventoryRepository.findByProductIdIn(productIds).stream()
                .map(InventoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // Thêm số lượng vào tồn kho
    @Override
    @Transactional
    public InventoryResponse addStock(StockUpdateRequest request) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");

        return circuitBreaker.run(() -> {
            Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
                    .orElse(new Inventory());

            if (inventory.getId() == null) {
                inventory.setProductId(request.getProductId());
                inventory.setQuantity(request.getQuantity());
                inventory.setReservedQuantity(0);
            } else {
                inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
            }

            Inventory savedInventory = inventoryRepository.save(inventory);
            return InventoryResponse.fromEntity(savedInventory);
        }, throwable -> {
            // Xử lý fallback khi có lỗi
            InventoryResponse fallbackResponse = new InventoryResponse();
            fallbackResponse.setProductId(request.getProductId());
            fallbackResponse.setQuantity(0);
            fallbackResponse.setReservedQuantity(0);
            fallbackResponse.setAvailableQuantity(0);
            fallbackResponse.setInStock(false);
            return fallbackResponse;
        });
    }

    // Đặt trước số lượng sản phẩm
    @Override
    @Transactional
    public InventoryResponse reserveStock(StockUpdateRequest request) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");

        return circuitBreaker.run(() -> {
            Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho sản phẩm: " + request.getProductId()));

            if (inventory.getAvailableQuantity() < request.getQuantity()) {
                throw new RuntimeException("Không đủ tồn kho cho sản phẩm: " + request.getProductId());
            }

            inventory.setReservedQuantity(inventory.getReservedQuantity() + request.getQuantity());
            Inventory savedInventory = inventoryRepository.save(inventory);
            return InventoryResponse.fromEntity(savedInventory);
        }, throwable -> {
            // Xử lý fallback khi có lỗi
            InventoryResponse fallbackResponse = new InventoryResponse();
            fallbackResponse.setProductId(request.getProductId());
            fallbackResponse.setInStock(false);
            return fallbackResponse;
        });
    }

    // Xác nhận giảm số lượng tồn kho
    @Override
    @Transactional
    public InventoryResponse confirmStockReduction(StockUpdateRequest request) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");

        return circuitBreaker.run(() -> {
            Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho sản phẩm: " + request.getProductId()));

            if (inventory.getReservedQuantity() < request.getQuantity()) {
                throw new RuntimeException("Không thể xác nhận nhiều hơn số lượng đã đặt trước cho sản phẩm: " + request.getProductId());
            }

            inventory.setQuantity(inventory.getQuantity() - request.getQuantity());
            inventory.setReservedQuantity(inventory.getReservedQuantity() - request.getQuantity());
            Inventory savedInventory = inventoryRepository.save(inventory);
            return InventoryResponse.fromEntity(savedInventory);
        }, throwable -> {
            // Xử lý fallback khi có lỗi
            InventoryResponse fallbackResponse = new InventoryResponse();
            fallbackResponse.setProductId(request.getProductId());
            return fallbackResponse;
        });
    }

    // Hủy đặt trước số lượng sản phẩm
    @Override
    @Transactional
    public InventoryResponse cancelReservation(StockUpdateRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho sản phẩm: " + request.getProductId()));

        if (inventory.getReservedQuantity() < request.getQuantity()) {
            throw new RuntimeException("Không thể hủy nhiều hơn số lượng đã đặt trước cho sản phẩm: " + request.getProductId());
        }

        inventory.setReservedQuantity(inventory.getReservedQuantity() - request.getQuantity());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.fromEntity(savedInventory);
    }
}
