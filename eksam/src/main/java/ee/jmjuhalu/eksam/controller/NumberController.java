package ee.jmjuhalu.eksam.controller;

import ee.jmjuhalu.eksam.entity.Conversion;
import ee.jmjuhalu.eksam.entity.Number;
import ee.jmjuhalu.eksam.repository.ConversionRepository;
import ee.jmjuhalu.eksam.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumberController {

    @Autowired
    private NumberRepository numberRepository;

    @Autowired
    private ConversionRepository conversionRepository;

    @GetMapping("number")
    public List<Number> getNumbers() {
        return numberRepository.findAll();
    }

    @PostMapping("number")
    public List<Number> addNumber(@RequestBody Number number) {
        if (number.getId() != null) {
            throw new RuntimeException("Cannot add with ID");
        }
        if (number.getArv() < 0) {
            throw new RuntimeException("Number ei saa olla negatiivne");
        }
        if (number.getArv() > 1000000) {
            throw new RuntimeException("Number on liiga suur");
        }
        numberRepository.save(number);
        return numberRepository.findAll();
    }

    @GetMapping("convert")
    public List<Conversion> convertNumbers(@RequestParam String type) {
        List<Number> numbers = numberRepository.findAll();
        List<Conversion> conversions = new ArrayList<>();

        for (Number number : numbers) {
            String convertedValue;

            if (type.equalsIgnoreCase("binary")) {
                convertedValue = Integer.toBinaryString(number.getArv());
            } else if (type.equalsIgnoreCase("octal")) {
                convertedValue = Integer.toOctalString(number.getArv());
            } else if (type.equalsIgnoreCase("hex")) {
                convertedValue = Integer.toHexString(number.getArv());
            } else {
                throw new RuntimeException("Vale conversion");
            }

            Conversion conversion = new Conversion();
            conversion.setOriginalNumber(number.getArv());
            conversion.setConversionType(type.toLowerCase());
            conversion.setConvertedValue(convertedValue);

            conversionRepository.save(conversion);
            conversions.add(conversion);
        }

        return conversions;
    }

    @GetMapping("conversions")
    public List<Conversion> getConversions() {
        return conversionRepository.findAll();
    }
}