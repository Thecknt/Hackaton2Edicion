package hackaton.Viajes.repository;

import hackaton.Viajes.model.TouristicService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristicServiceRepository extends JpaRepository<TouristicService, Integer> {
}
