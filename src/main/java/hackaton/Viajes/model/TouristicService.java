package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTouristicService;
    private Integer idCodeService;
    private String name;
    private String briefDescription;
    private String serviceDestination;
    private Date serviceDate;
    private Double priceCost;

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
