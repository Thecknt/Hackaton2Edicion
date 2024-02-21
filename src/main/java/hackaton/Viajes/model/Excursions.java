package hackaton.Viajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Excursions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExcursion;
    private String typeCircuit;
    private Double priceExcursions;
}
