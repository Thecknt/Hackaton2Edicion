package hackaton.Viajes.service;

import hackaton.Viajes.model.Event;
import hackaton.Viajes.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventService implements IEventService{

    @Autowired
    EventsRepository eventsRepository;

    @Override
    public List<Event> events() {
        return eventsRepository.findAll();
    }

    @Override
    public Event findById(Integer idEvent) {
        Event event = eventsRepository.findById(idEvent).orElse(null);
        return event;
    }

    @Override
    public void save(Event event) {
      eventsRepository.save(event);
    }

    @Override
    public void delete(Event event) {
      eventsRepository.delete(event);
    }
}
