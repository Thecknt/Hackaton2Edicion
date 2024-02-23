package hackaton.Viajes.model;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Person {
    protected String name;
    protected String lastname;
    protected String address;
    protected String dni;
    protected LocalDate dateOfBird;
    protected String nationality;
    protected String celuphone;
    protected String email;
}
