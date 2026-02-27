package ee.jmjuhalu.decathlon.repository;

import ee.jmjuhalu.decathlon.entity.Sportlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportlaneRepository extends JpaRepository<Sportlane, Long> {
}