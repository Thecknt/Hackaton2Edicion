package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "excursion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Excursion{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idExcursion;
    private String typeCircuit;
    private String nameOfCircuit;
    private Double priceOfCircuit;
    private Integer amountOfTickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTouristicService")
    private TouristicService touristicService;
}
