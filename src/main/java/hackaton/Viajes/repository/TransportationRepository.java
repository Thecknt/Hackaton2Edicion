package hackaton.Viajes.repository;

import hackaton.Viajes.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationRepository extends JpaRepository<Transportation, Integer> {
}
