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
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer numSales;
    private Date salesDate;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "idClient")
    Client client;

    @ManyToOne
    @JoinColumn(name = "idTouristicService")
    TouristicService touristicService;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    Employee employee;
}
