package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "transportation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTransportation;
    private Double priceTransportation;
    private String typeOfTransport;
    private Integer amountOfTickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTouristicService")
    private TouristicService touristicService;
}
