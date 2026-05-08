package ee.jmjuhalu.decathlon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kohtunik {
    private Long id;
    private String nimi;
    private String riik;
    private Integer kogemus;
}