package ee.jmjuhalu.decathlon.controller;

import ee.jmjuhalu.decathlon.entity.Sportlane;
import ee.jmjuhalu.decathlon.entity.Tulemus;
import ee.jmjuhalu.decathlon.repository.SportlaneRepository;
import ee.jmjuhalu.decathlon.service.SportlaneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class SportlaneController {

    private SportlaneRepository sportlaneRepository;
    private SportlaneService sportlaneService;

    @GetMapping("sportlased")
    public Page<Sportlane> saaSportlased(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String riik,
            @RequestParam(defaultValue = "nimi") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        List<Sportlane> sportlased = sportlaneRepository.findAll();

        if (riik != null && !riik.isEmpty()) {
            sportlased = sportlased.stream()
                    .filter(sportlane -> sportlane.getRiik() != null)
                    .filter(sportlane -> sportlane.getRiik().equalsIgnoreCase(riik))
                    .toList();
        }

        Comparator<Sportlane> comparator;

        if (sortBy.equalsIgnoreCase("punktid")) {
            comparator = Comparator.comparingDouble(this::arvutaKoguPunktid);
        } else {
            comparator = Comparator.comparing(Sportlane::getNimi, String.CASE_INSENSITIVE_ORDER);
        }

        if (direction.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }

        sportlased = sportlased.stream()
                .sorted(comparator)
                .toList();

        int start = page * size;
        int end = Math.min(start + size, sportlased.size());

        List<Sportlane> leheSportlased;

        if (start > sportlased.size()) {
            leheSportlased = List.of();
        } else {
            leheSportlased = sportlased.subList(start, end);
        }

        return new PageImpl<>(leheSportlased, PageRequest.of(page, size), sportlased.size());
    }

    @PostMapping("sportlased")
    public List<Sportlane> lisaSportlane(@RequestBody Sportlane sportlane) {

        if (sportlane.getId() != null) {
            throw new RuntimeException("Ei saa lisada ID-ga sportlast");
        }

        if (sportlane.getNimi() == null || sportlane.getNimi().isEmpty()) {
            throw new RuntimeException("Nimi on kohustuslik");
        }

        if (sportlane.getRiik() == null || sportlane.getRiik().isEmpty()) {
            throw new RuntimeException("Riik on kohustuslik");
        }

        sportlaneRepository.save(sportlane);
        return sportlaneRepository.findAll();
    }

    @DeleteMapping("sportlased/{id}")
    public List<Sportlane> kustutaSportlane(@PathVariable Long id) {
        sportlaneRepository.deleteById(id);
        return sportlaneRepository.findAll();
    }

    @PostMapping("sportlased/{id}/tulemus")
    public Sportlane lisaTulemus(@PathVariable Long id,
                                 @RequestBody Tulemus tulemus) {
        return sportlaneService.lisaTulemus(id, tulemus);
    }

    @GetMapping("sportlased/{id}/kogusumma")
    public double koguPunktid(@PathVariable Long id) {
        return sportlaneService.koguPunktid(id);
    }

    private double arvutaKoguPunktid(Sportlane sportlane) {
        if (sportlane.getTulemused() == null) {
            return 0;
        }

        return sportlane.getTulemused()
                .stream()
                .filter(tulemus -> tulemus.getPunktid() != null)
                .mapToDouble(Tulemus::getPunktid)
                .sum();
    }
}