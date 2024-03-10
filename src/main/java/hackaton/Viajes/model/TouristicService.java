package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "touristic_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristicService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer idTouristicService;
    protected String name;
    protected String briefDescription;
    protected String serviceDestination;
    protected Date serviceDate;
    protected Double priceCost;

    @OneToMany(mappedBy = "touristicService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hotel> hotels = new ArrayList<>();

    @OneToMany(mappedBy = "touristicService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Excursion> excursions = new ArrayList<>();

    @OneToMany(mappedBy = "touristicService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarRental> carRentals = new ArrayList<>();

    @OneToMany(mappedBy = "touristicService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTourPackage")
    private TourPackage tourPackage;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idClient")
    Client client;
}
