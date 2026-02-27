package ee.jmjuhalu.veebipood.repository;

import ee.jmjuhalu.veebipood.entity.Person;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

//non-null type argument is expected
//Spring boot 4.0
// CrudRepository --> minimaalsed vajalikud (standardsed) funktsioonid
// PagingAndSortingRepository --> funktsioonid lehekülgede andmete väljastamiseks ja sorteerimiseks
// JpaRepository --> kõikvõimalikud funktsioonid
public interface PersonRepository extends JpaRepository<@NonNull Person, @NonNull Long> {
    Person findByEmail (String email);
}
