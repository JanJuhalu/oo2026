package ee.jmjuhalu.eksam.repository;

import ee.jmjuhalu.eksam.entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
}