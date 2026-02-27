package ee.jmjuhalu.auto.repository;

import ee.jmjuhalu.auto.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface autoRepository extends JpaRepository<Auto, Long> {
}