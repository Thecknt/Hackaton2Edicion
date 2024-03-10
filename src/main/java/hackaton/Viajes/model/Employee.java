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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idEmployee;
    private String position;
    private Double salary;
    private String name;
    private String lastname;
    private String address;
    private String dni;
    private LocalDate dateOfBird;
    private String nationality;
    private String celuphone;

//    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
//    private UserEntity user;
}
