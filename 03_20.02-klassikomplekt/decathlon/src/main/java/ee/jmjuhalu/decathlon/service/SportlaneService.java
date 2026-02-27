package ee.jmjuhalu.decathlon.service;

import ee.jmjuhalu.decathlon.entity.Sportlane;
import ee.jmjuhalu.decathlon.entity.Tulemus;
import ee.jmjuhalu.decathlon.repository.SportlaneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class SportlaneService {

    private SportlaneRepository sportlaneRepository;

    public Sportlane lisaTulemus(Long sportlaneId, Tulemus tulemus) {

        Sportlane sportlane = sportlaneRepository.findById(sportlaneId)
                .orElseThrow(() -> new RuntimeException("Sportlast ei leitud"));

        if (tulemus.getSpordiala() == null || tulemus.getSpordiala().isEmpty()) {
            throw new RuntimeException("Spordiala on kohustuslik");
        }

        if (tulemus.getTulemus() == null || tulemus.getTulemus() <= 0) {
            throw new RuntimeException("Tulemus peab olema positiivne number");
        }

        double punktid = arvutaPunktid(tulemus.getSpordiala(), tulemus.getTulemus());
        tulemus.setPunktid(punktid);

        if (sportlane.getTulemused() == null) {
            sportlane.setTulemused(new ArrayList<>());
        }

        sportlane.getTulemused().add(tulemus);

        return sportlaneRepository.save(sportlane);
    }

    private double arvutaPunktid(String ala, Double tulemus) {

        if (ala.equalsIgnoreCase("100m")) {
            return 1000 - tulemus * 10;  // lihtsustatud loogika
        }

        if (ala.equalsIgnoreCase("kaugushüpe")) {
            return tulemus * 100;
        }

        throw new RuntimeException("Tundmatu spordiala");
    }

    public double koguPunktid(Long sportlaneId) {
        Sportlane sportlane = sportlaneRepository.findById(sportlaneId)
                .orElseThrow(() -> new RuntimeException("Sportlast ei leitud"));

        if (sportlane.getTulemused() == null) {
            return 0;
        }

        return sportlane.getTulemused()
                .stream()
                .mapToDouble(Tulemus::getPunktid)
                .sum();
    }
}