package ee.jmjuhalu.veebipood.repository;

import ee.jmjuhalu.veebipood.entity.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

//non-null type argument is expected
//Spring boot 4.0
public interface CategoryRepository extends JpaRepository<@NonNull Category, @NonNull Long> {
}
