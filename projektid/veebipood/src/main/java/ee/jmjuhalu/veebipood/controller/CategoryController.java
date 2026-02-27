package ee.jmjuhalu.veebipood.controller;

import ee.jmjuhalu.veebipood.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//localhost: 8080
//application.properties server.port=8090

public class CategoryController {
    @Autowired

    private ee.jmjuhalu.veebipood.repository.CategoryRepository categoryRepository;


    @GetMapping("categories")
    public List<Category> getCategories(){

        return categoryRepository.findAll();
    }

    @DeleteMapping("categories/{id}")
    public List<Category> deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id); //kustutan
        return categoryRepository.findAll(); //uuenenud seis
    }

    @PostMapping("categories")
    public List<Category> addcategory(@RequestBody Category category){
        categoryRepository.save(category); //siin salvesatab
        return categoryRepository.findAll(); //siin on uuenenud seis
    }

}
