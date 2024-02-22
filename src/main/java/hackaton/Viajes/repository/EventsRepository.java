package hackaton.Viajes.repository;

import hackaton.Viajes.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Integer> {
}
