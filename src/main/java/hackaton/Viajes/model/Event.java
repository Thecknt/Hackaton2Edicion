package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@PrimaryKeyJoinColumn(name = "idTouristicService")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event extends TouristicService{
    private String typeEvent;
    private String nameOfEvent;
    private Double priceTicket;
    private Integer amountOfTickets;
}
