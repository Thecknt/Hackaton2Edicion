package hackaton.Viajes.service;

import hackaton.Viajes.model.Event;
import java.util.List;

public interface IEventService {

   public List<Event> events();

   public Event findById(Integer idEvent);

   public void save(Event event);

   public void delete(Event event);

}
