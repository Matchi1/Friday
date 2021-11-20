package fr.umlv.main.event;

import java.util.Objects;
import java.util.UUID;

public record EventResponseDTO(UUID id) {
    public EventResponseDTO {
        Objects.requireNonNull(id);
    }
}
