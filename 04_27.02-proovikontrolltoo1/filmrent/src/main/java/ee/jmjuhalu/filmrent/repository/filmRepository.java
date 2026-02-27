package ee.jmjuhalu.filmrent.repository;

import ee.jmjuhalu.filmrent.entity.Film;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface filmRepository extends JpaRepository<@NonNull Film, @NonNull Long>  {

}
