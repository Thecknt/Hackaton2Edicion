package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCodeService;
    private String name;
    private String briefDescription;
    private String serviceDestination;
    private Date serviceDate;
    private Double priceCost;

    @OneToOne
    TourPackage tourPackage;
}
