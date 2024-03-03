package hackaton.Viajes.repository;

import hackaton.Viajes.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Event, Integer> {
}
