package iuh.fit.se.shippingservice.repositories;

import iuh.fit.se.shippingservice.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    // Tìm đơn vận chuyển theo mã đơn hàng
    Optional<Shipment> findByOrderId(String orderId);

    // Tìm đơn vận chuyển theo mã theo dõi
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
}
