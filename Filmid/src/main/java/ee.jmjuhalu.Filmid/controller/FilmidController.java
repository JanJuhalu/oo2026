package ee.jmjuhalu.Filmid.controller;

import ee.jmjuhalu.Filmid.entity.Filmid;
import ee.jmjuhalu.Filmid.repository.FilmidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

    //localhost: 8080
    //application.properties server.port=8090

public class FilmidController {

//    @GetMapping("products")
//    public String helloworld(){
//        return "Hello World";
//    }

    @Autowired

    private FilmidRepository filmidRepository;


    @GetMapping("filmid")
    public List<Filmid> getFilmid(){

        return filmidRepository.findAll();
    }

    @DeleteMapping("filmid/{id}")
    public List<Filmid> deleteFilmid(@PathVariable Long id){
        filmidRepository.deleteById(id); //kustutan
        return filmidRepository.findAll(); //uuenenud seis
    }

    @PostMapping("filmid")
    public List<Filmid> addFilmid(@RequestBody Filmid filmid){
        filmidRepository.save(filmid); //siin salvesatab
        return filmidRepository.findAll(); //siin on uuenenud seis
    }

}
