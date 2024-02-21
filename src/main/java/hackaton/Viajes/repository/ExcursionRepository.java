package hackaton.Viajes.repository;

import hackaton.Viajes.model.Excursions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository extends JpaRepository<Excursions, Integer> {
    
}
