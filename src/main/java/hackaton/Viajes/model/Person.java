package hackaton.Viajes.model;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    private String name;
    private String lastname;
    private String address;
    private String dni;
    private LocalDate dateOfBird;
    private String nationality;
    private String celuphone;
    private String email;
}
