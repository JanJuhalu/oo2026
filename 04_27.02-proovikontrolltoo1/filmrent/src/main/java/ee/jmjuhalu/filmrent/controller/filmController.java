package ee.jmjuhalu.filmrent.controller;

import ee.jmjuhalu.filmrent.dto.FilmSaveDto;
import ee.jmjuhalu.filmrent.entity.Film;
import ee.jmjuhalu.filmrent.entity.FilmType;
import ee.jmjuhalu.filmrent.repository.filmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class filmController {

    private final filmRepository filmRepository;

    @PostMapping("films")
    public Film saveFilm(@RequestBody FilmSaveDto filmSaveDto) {

        Film film = new Film();
        film.setTitle(filmSaveDto.title());
        film.setType(filmSaveDto.type());
        film.setDays(0); // selle nimel tegime recordi
        return filmRepository.save(film);


    }

    @DeleteMapping("films/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }

    //PUT -->kogu entity muutmise võimekus
    //PATCH --> booking võetud, tellimus makstud, kogus ühe võrra vähendatud
    @PatchMapping("films/type")
    public Film chaneFilmType(@RequestParam Long id, @RequestParam FilmType filmType) {
        Film film = filmRepository.findById(id).orElseThrow();
        film.setType(filmType);
        return filmRepository.save(film);
    }

    @GetMapping("films")
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @GetMapping("films/available")
    public List<Film> findAllAvailable() {
        return filmRepository.findByDays(0);

    }
}
