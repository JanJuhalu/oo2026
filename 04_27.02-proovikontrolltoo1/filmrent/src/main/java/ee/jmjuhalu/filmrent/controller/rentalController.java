package ee.jmjuhalu.filmrent.controller;

import ee.jmjuhalu.filmrent.dto.FilmRentalDto;
import ee.jmjuhalu.filmrent.entity.Film;
import ee.jmjuhalu.filmrent.entity.Rental;
import ee.jmjuhalu.filmrent.repository.filmRepository;
import ee.jmjuhalu.filmrent.repository.rentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static ee.jmjuhalu.filmrent.entity.FilmType.*;

@RestController
@RequiredArgsConstructor
public class rentalController {

    private final filmRepository filmRepository;
    private rentalRepository rentalRepository;
    private double premiumPrice = 4;
    private double basicPrice = 3;

    @GetMapping("rental")
    public List<Rental> findAll(){
        return rentalRepository.findAll();
    }

    //DTO --> mis fil(id), mitu päeva rendiks soovin

    @PostMapping("start-rental")
    public  Rental startRental(@RequestBody List<FilmRentalDto> filmRentalDtos){

        Rental rental = new Rental();
        Rental dbRental = rentalRepository.save(rental);

        double sum = 0;

        //mis tüüp            muutuja           mida läbi käin
        for (FilmRentalDto filmRentalDto : filmRentalDtos){
            Film dbFilm = filmRepository.findById(filmRentalDto.filmId())
                    .orElseThrow();
            dbFilm.setRental(dbRental);
            dbFilm.setDays(filmRentalDto.days());
            switch (dbFilm.getType()){
                case NEW -> sum += premiumPrice * filmRentalDto.days();
                case REGULAR -> {
                    if (filmRentalDto.days() <= 3){
                        sum += basicPrice;

                    } else {
                        sum += basicPrice + basicPrice * filmRentalDto.days() -3;
                    }
                }
                case OLD -> {
                    if (filmRentalDto.days() <= 5){
                        sum += basicPrice;

                    } else {
                        sum += basicPrice + basicPrice * filmRentalDto.days() - 5;
                    }
                }

            }
            filmRepository.save(dbFilm);


        }

        dbRental.setInitialFee(sum);
        return rentalRepository.save(dbRental);
    }
//DTO --> mis fil(id), mitu päeva rendis tegeliult oli
    @PostMapping("end-rental")
    public double endRental(@RequestBody List<FilmRentalDto> filmRentalDtos){

        double sum = 0;
        for (FilmRentalDto filmRentalDto : filmRentalDtos){
            Film dbFilm = filmRepository.findById(filmRentalDto.filmId()).orElseThrow();
            Rental rental =dbFilm.getRental();
            rental.setLateFee(rental.getLateFee() + FILMI_SUMMA);
            rentalRepository.save(rental);

            dbFilm.setRental(null);
            dbFilm.setDays(0);
            filmRepository.save(dbFilm);
        }
        return sum;  //maksmisele minev summa (võib tulla erinevatest rentalitest)
    }
}
