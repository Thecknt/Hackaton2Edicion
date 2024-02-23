package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristicService {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer idTouristicService;
    protected Integer idCodeService;
    protected String name;
    protected String briefDescription;
    protected String serviceDestination;
    protected Date serviceDate;
    protected Double priceCost;

    @ManyToOne
    @JoinColumn(name = "idTourPackage")
    TourPackage tourPackage;

    @ManyToOne
    @JoinColumn(name = "idHotel")
    Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "idCarRental")
    CarRental carRental;

    @ManyToOne
    @JoinColumn(name = "idTransportation")
    Transportation transportation;

    @ManyToOne
    @JoinColumn(name = "idEvent")
    Event event;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "idClient")
    Client client;
}
