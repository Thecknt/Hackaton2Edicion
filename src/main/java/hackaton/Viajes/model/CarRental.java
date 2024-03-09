package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "car_rental")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCarRental;
    private Integer amountOfDays;
    private String typeOfCar;
    private Integer priceByDay;
    private Boolean rent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTouristicService")
    private TouristicService touristicService;
}
