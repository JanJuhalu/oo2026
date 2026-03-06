package ee.jmjuhalu.eksam.repository;

import ee.jmjuhalu.eksam.entity.Number;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberRepository extends JpaRepository<@NonNull Number, @NonNull Long> {
}