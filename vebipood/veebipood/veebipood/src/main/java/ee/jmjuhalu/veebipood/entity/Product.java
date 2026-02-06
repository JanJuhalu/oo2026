package ee.jmjuhalu.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean active;
    private Integer stock;
    //Üanen Andmebaasi aga ei määra selle väärtust:
    //double --> 0
    //Boolean --> false
    // int --> 0

    //Panen andmeaasi aga ei määra selle väärtust:
    //double --> null
    //Boolean --> null
    // int --> null
}
