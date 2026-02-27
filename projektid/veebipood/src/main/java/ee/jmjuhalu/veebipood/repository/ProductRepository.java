package ee.jmjuhalu.veebipood.repository;

import ee.jmjuhalu.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// repository --> andmebaasiga suhtlemiseks, tema sees on küik funktsioonid, mida on võimalik andmebaasiga teha


public interface ProductRepository extends
        JpaRepository<Product, Long> {

}

