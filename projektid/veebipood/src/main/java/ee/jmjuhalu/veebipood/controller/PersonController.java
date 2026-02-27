package ee.jmjuhalu.veebipood.controller;

import ee.jmjuhalu.veebipood.dto.PersonLoginRecordDto;
import ee.jmjuhalu.veebipood.entity.Person;
import ee.jmjuhalu.veebipood.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController

//localhost: 8080
//application.properties server.port=8090

public class PersonController {
    @Autowired

    private ee.jmjuhalu.veebipood.repository.PersonRepository personRepository;
    // Dependency Injection. Kui luuakse see klass (PersonController), seotakse ära samal ajal
    // temaga kõik allolevad muutujad
    // Injectiga võib ka läbi ka constructorite
    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPersons(){

        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePersons(@PathVariable Long id){
        personRepository.deleteById(id); //kustutan
        return personRepository.findAll(); //uuenenud seis
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        personService.validate(person);
        return personRepository.save(person);
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personLoginRecordDto){
        Person dbPerson = personRepository.findByEmail(personLoginRecordDto.email());
        if (dbPerson == null) {
            throw new RuntimeException("Invalid Email");
        }
        if (!dbPerson.getPassword().equals(personLoginRecordDto.password())){
            throw new RuntimeException("Invalid password");
        }
        return dbPerson;
    }
}
