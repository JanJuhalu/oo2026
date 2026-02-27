package ee.jmjuhalu.veebipood.controller;

import ee.jmjuhalu.veebipood.entity.Category;
import ee.jmjuhalu.veebipood.entity.Product;
import ee.jmjuhalu.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

    //localhost: 8080
    //application.properties server.port=8090

public class productController {

/*
    @GetMapping("products")
    public String helloworld(){
        return "Hello World";
    }
*/
// 1xx -> (harva) informatiivne
// 2xx -> õnnestuv
// 3xx -> (harva) redirect
// 4xx -> päringu tegija (client error / front-end error)
// 5xx -> päringu vastuvõtja viga (server error)
    @Autowired

    private ProductRepository productRepository;


    @GetMapping("products")
    public List<Product> getProducts(){

        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOneProducts(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProducts(@PathVariable Long id){
        productRepository.deleteById(id); //kustutan
        return productRepository.findAll(); //uuenenud seis
    }

    @PostMapping("products")
    public List<Product> addProducts(@RequestBody Product product){
        if (product.getId()!=null){
            throw new RuntimeException("Cannot add with ID");

        }
        productRepository.save(product); //siin salvesatab
        return productRepository.findAll(); //siin on uuenenud seis
    }

    @PutMapping("products")
    public List<Product> editProducts(@RequestBody Product product){
        //file -> settings -> plugins -> lombok
        if (product.getId()==null){
            throw new RuntimeException("Cannot edit without ID");

        }
        if (!productRepository.existsById(product.getId())){
            throw new RuntimeException("Product ID does not exist");
        }
        productRepository.save(product); //siin salvesatab
        return productRepository.findAll(); //siin on uuenenud seis
    }

}
