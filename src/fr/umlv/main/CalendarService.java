package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CalendarService {
    @Autowired
    private EventRepo eventRepository;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public void updateEvent(UUID uid, Event event) {
        eventRepository.save(event);
    }

    public void removeEvent(UUID uid) {
        eventRepository.deleteById(uid);
    }
}
