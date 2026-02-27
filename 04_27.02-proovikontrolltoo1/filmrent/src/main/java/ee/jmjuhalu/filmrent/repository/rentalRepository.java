package ee.jmjuhalu.filmrent.repository;

import ee.jmjuhalu.filmrent.entity.Rental;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface rentalRepository extends JpaRepository<@NonNull Rental, @NonNull Long>  {


}
