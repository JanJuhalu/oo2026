package ee.jmjuhalu.auto.controller;

import ee.jmjuhalu.auto.entity.Auto;
import ee.jmjuhalu.auto.repository.autoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class autoController {

    @Autowired
    private autoRepository autoRepository;

    // GET - kõik autod
    @GetMapping("autod")
    public List<Auto> getAutod() {
        return autoRepository.findAll();
    }

    @DeleteMapping("autod/{id}")
    public List<Auto> deleteAutod(@PathVariable Long id){
        autoRepository.deleteById(id); //kustutan
        return autoRepository.findAll(); //uuenenud seis
    }

    // POST - lisa uus auto
    @PostMapping("autod")
    public List<Auto> addAuto(@RequestBody Auto auto) {

        if (auto.getId() != null) {
            throw new RuntimeException("Cannot add auto with ID");
        }

        if (auto.getBrand() == null || auto.getBrand().isEmpty()) {
            throw new RuntimeException("Brand is required");
        }

        if (auto.getModel() == null || auto.getModel().isEmpty()) {
            throw new RuntimeException("Model is required");
        }

        if (auto.getProductionYear() == null || auto.getProductionYear() < 1886 || auto.getProductionYear() > 2026) {
            throw new RuntimeException("Invalid productionyear");
        }

        if (auto.getPrice() == null || auto.getPrice() <= 0) {
            throw new RuntimeException("Price must be greater than 0");
        }

        if (auto.getMileage() == null || auto.getMileage() < 0) {
            throw new RuntimeException("Mileage cannot be negative");
        }

        autoRepository.save(auto);
        return autoRepository.findAll();
    }

}