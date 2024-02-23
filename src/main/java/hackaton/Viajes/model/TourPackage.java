package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idTourPackage;

    @OneToMany(mappedBy = "tourPackage")
    private List<TouristicService> services;

    private Double tourPackageCost;


}
