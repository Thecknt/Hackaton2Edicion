package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEvent;
    private String typeEvent;
    private String nameOfEvent;
    private Double priceTicket;
    private Integer amountOfTickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTouristicService")
    private TouristicService touristicService;
}
