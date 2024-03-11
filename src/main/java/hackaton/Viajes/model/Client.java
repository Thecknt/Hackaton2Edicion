package hackaton.Viajes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idClient;
    private String name;
    private String lastname;
    private String address;
    private String dni;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    private String nationality;
    private String celuphone;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    private UserEntity user;
}
