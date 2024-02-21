package hackaton.Viajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transportation extends TouristicService{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransportation;
    private Double priceTransportation;
    private String typeOfTransport;
}
