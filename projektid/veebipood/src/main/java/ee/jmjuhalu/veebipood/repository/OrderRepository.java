package ee.jmjuhalu.veebipood.repository;

import ee.jmjuhalu.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
