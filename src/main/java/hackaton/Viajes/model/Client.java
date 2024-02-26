package hackaton.Viajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idClient;
    private String name;
    private String lastname;
    private String address;
    private String dni;
    private LocalDate dateOfBird;
    private String nationality;
    private String celuphone;
    private String email;

}
