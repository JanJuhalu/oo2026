package ee.jmjuhalu.filmrent.repository;

import ee.jmjuhalu.filmrent.entity.Film;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface filmRepository extends JpaRepository<@NonNull Film, @NonNull Long>  {

    //SELECT * from film WHERE days =
    List<Film > findByDays(Integer days);

}
