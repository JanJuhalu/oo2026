package ee.jmjuhalu.filmrent.controller;

import ee.jmjuhalu.filmrent.dto.FilmSaveDto;
import ee.jmjuhalu.filmrent.entity.Film;
import ee.jmjuhalu.filmrent.repository.filmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class filmController {

    private final filmRepository filmRepository;

    @PostMapping("films")
    public Film saveFilm(@RequestBody FilmSaveDto filmSaveDto){

        Film film = new Film();
        film.setTitle(filmSaveDto.title());
        film.setType(filmSaveDto.type());
        film.setDays(0); // selle nimel tegime recordi
        return filmRepository.save(film);


    }
    @DeleteMapping("films/{id}")
    public void deleteFilm(@PathVariable Long id){
        filmRepository.deleteById(id);
    }
}
