package ee.jmjuhalu.decathlon.controller;

import ee.jmjuhalu.decathlon.entity.Sportlane;
import ee.jmjuhalu.decathlon.entity.Tulemus;
import ee.jmjuhalu.decathlon.repository.SportlaneRepository;
import ee.jmjuhalu.decathlon.service.SportlaneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SportlaneController {

    private SportlaneRepository sportlaneRepository;
    private SportlaneService sportlaneService;

    // Lisa uus sportlane
    @PostMapping("sportlased")
    public List<Sportlane> lisaSportlane(@RequestBody Sportlane sportlane) {

        if (sportlane.getId() != null) {
            throw new RuntimeException("Ei saa lisada ID-ga sportlast");
        }

        if (sportlane.getNimi() == null || sportlane.getNimi().isEmpty()) {
            throw new RuntimeException("Nimi on kohustuslik");
        }

        sportlaneRepository.save(sportlane);
        return sportlaneRepository.findAll();
    }

    // Lisa tulemus sportlasele
    @PostMapping("sportlased/{id}/tulemus")
    public Sportlane lisaTulemus(@PathVariable Long id,
                                 @RequestBody Tulemus tulemus) {
        return sportlaneService.lisaTulemus(id, tulemus);
    }

    // Kogu punktisumma
    @GetMapping("sportlased/{id}/kogusumma")
    public double koguPunktid(@PathVariable Long id) {
        return sportlaneService.koguPunktid(id);
    }
}