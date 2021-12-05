package fr.umlv.main.back.event;

import java.util.Date;
import java.util.UUID;

public record EventResponseDTO(UUID id, String title, Date start, Date end, String information) {
    public EventResponseDTO(Event event) {
        this(event.getId(), event.getTitle(), event.getDateStart(), event.getDateEnd(), event.getInfo());
    }
}
