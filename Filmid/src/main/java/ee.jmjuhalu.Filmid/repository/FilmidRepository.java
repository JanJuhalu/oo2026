package ee.jmjuhalu.Filmid.repository;

import ee.jmjuhalu.Filmid.entity.Filmid;
import org.springframework.data.jpa.repository.JpaRepository;

// repository --> andmebaasiga suhtlemiseks, tema sees on
// küik funktsioonid, mida on võimalik andmebaasiga teha


public interface FilmidRepository extends
        JpaRepository<Filmid, Long> {

}

