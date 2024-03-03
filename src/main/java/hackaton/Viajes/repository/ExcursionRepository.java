package hackaton.Viajes.repository;

import hackaton.Viajes.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Integer> {
    
}
