package hackaton.Viajes.repository;

import hackaton.Viajes.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Integer> {
}
