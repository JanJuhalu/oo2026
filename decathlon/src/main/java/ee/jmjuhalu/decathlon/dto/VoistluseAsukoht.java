package ee.jmjuhalu.decathlon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoistluseAsukoht {
    private Long id;
    private String nimi;
    private String linn;
    private String riik;
    private String staadion;
}