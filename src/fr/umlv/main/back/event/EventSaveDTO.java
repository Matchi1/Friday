package fr.umlv.main.back.event;

import fr.umlv.main.DateDetails;

import java.util.Objects;

public record EventSaveDTO(DateDetails dateStart, DateDetails dateEnd, String info) {
    public EventSaveDTO {
        Objects.requireNonNull(dateStart);
        Objects.requireNonNull(dateEnd);
        Objects.requireNonNull(info);
    }

    public EventSaveDTO(DateDetails dateStart, String info) {
        this(dateStart, dateStart, info);
    }
}
