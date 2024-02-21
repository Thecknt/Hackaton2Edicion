package hackaton.Viajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTourPackage;
    private List<TouristicService> services;
    private Double tourPackageCost;
}
