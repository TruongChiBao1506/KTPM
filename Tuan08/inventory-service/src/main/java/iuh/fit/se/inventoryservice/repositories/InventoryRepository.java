package iuh.fit.se.inventoryservice.repositories;

import iuh.fit.se.inventoryservice.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Tìm tồn kho theo mã sản phẩm
    Optional<Inventory> findByProductId(String productId);

    // Tìm tồn kho theo danh sách mã sản phẩm
    List<Inventory> findByProductIdIn(List<String> productIds);

}
