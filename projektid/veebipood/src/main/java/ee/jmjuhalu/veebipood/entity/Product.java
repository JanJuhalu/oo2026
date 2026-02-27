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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean active;
    private Integer stock;

    //@ManyToMany --> private List<Ingredients> ingredients
    //@OneToMany --> private List<Ingredients> ingredients
    //@ManyToOne --> tooted jagavd seda kategooriat
    //@OneToOne --> tooted ei jaga seda kategooriat

    @ManyToOne

    private Category category; //automaatselt võõrvõtmega(@id väljaga) siia tabelisse
    //Üanen Andmebaasi aga ei määra selle väärtust:
    //double --> 0
    //Boolean --> false
    // int --> 0

    //Panen andmeaasi aga ei määra selle väärtust:
    //double --> null
    //Boolean --> null
    // int --> null
}
