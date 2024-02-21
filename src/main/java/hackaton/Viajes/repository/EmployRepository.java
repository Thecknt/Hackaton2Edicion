package hackaton.Viajes.repository;

import hackaton.Viajes.model.Employ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployRepository extends JpaRepository<Employ, Integer> {
}
