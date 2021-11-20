package fr.umlv.main.event;

import fr.umlv.main.DateDetails;

import java.util.Objects;

public record EventSaveDTO(DateDetails dateStart, DateDetails dateEnd, String info) {
    public EventSaveDTO {
        Objects.requireNonNull(dateStart);
        Objects.requireNonNull(dateEnd);
        Objects.requireNonNull(info);
    }
}
