package ee.jmjuhalu.decathlon.controller;

import ee.jmjuhalu.decathlon.dto.Kohtunik;
import ee.jmjuhalu.decathlon.dto.VoistluseAsukoht;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MockApiController {

    @Value("${mockapi.url}")
    private String mockApiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("kohtunikud")
    public List<Kohtunik> saaKohtunikud() {
        String url = mockApiUrl + "/kohtunikud";
        Kohtunik[] response = restTemplate.exchange(url, HttpMethod.GET, null, Kohtunik[].class).getBody();
        return Arrays.asList(response);
    }

    @GetMapping("voistluste-asukohad")
    public List<VoistluseAsukoht> saaVoistlusteAsukohad() {
        String url = mockApiUrl + "/asukohad";
        VoistluseAsukoht[] response = restTemplate.exchange(url, HttpMethod.GET, null, VoistluseAsukoht[].class).getBody();
        return Arrays.asList(response);
    }
}