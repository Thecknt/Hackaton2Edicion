package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "package")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idTourPackage;

    @OneToMany(mappedBy = "tourPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TouristicService> services = new ArrayList<>();

    private Double tourPackageCost;

}
