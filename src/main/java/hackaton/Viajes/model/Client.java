package hackaton.Viajes.model;

import jakarta.persistence.*;
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

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
//    private UserEntity user;
}
