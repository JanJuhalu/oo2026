package ee.jmjuhalu.veebipood.controller;

import ee.jmjuhalu.veebipood.entity.Product;
import ee.jmjuhalu.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

    //localhost: 8080
    //application.properties server.port=8090

    public class productController {

//    @GetMapping("products")
//    public String helloworld(){
//        return "Hello World";
//    }

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProducts(@PathVariable Long id){
        productRepository.deleteById(id); //kustutan
        return productRepository.findAll(); //uuenenud seis
    }

    @PostMapping("products")
    public List<Product> addProducts(@RequestBody Product product){
        productRepository.save(product); //siin salvesatab
        return productRepository.findAll(); //siin on uuenenud seis
    }

}
