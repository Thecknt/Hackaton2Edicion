package hackaton.Viajes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel{
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer idHotel;
     private String stars;
     private Double priceHotel;
     private String description;
     private Integer numberOfNights;
     private String nameOfHotel;
     private String location;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "idTouristicService")
     private TouristicService touristicService;
}
