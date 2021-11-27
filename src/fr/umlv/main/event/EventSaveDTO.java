package fr.umlv.main.event;

public record EventSaveDTO(String date, String heure, String info) {
    EventSaveDTO (Event event) {
        this(event.getDate(), event.getHeure(), event.getInfo());
    }
}
