package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "touristic_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idTouristicService;
    protected String name;
    protected String briefDescription;
    protected String serviceDestination;
    protected Date serviceDate;
    protected Double priceCost;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idTourPackage")
    TourPackage tourPackage;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "idHotel")
//    Hotel hotel;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "idCarRental")
//    CarRental carRental;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "idTransportation")
//    Transportation transportation;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "idEvent")
//    Event event;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "idEmployee")
//    Employee employee;
//
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "idClient")
//    Client client;
}
