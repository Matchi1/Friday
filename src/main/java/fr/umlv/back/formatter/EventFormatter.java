package fr.umlv.back.formatter;

import fr.umlv.back.event.Event;

import java.util.StringJoiner;

public class EventFormatter {
    private final Object monitor = new Object();

    public String format(Event event) {
        synchronized (monitor) {
            var dateFormatter = new DateFormatterTheadSafe();
            var start = dateFormatter.format(event.getDateStart());
            var end = dateFormatter.format(event.getDateEnd());
            var format = new StringJoiner(":");
            var period = start.equals(end) ? start : start + "/" + end;
            format.add(event.getTitle());
            format.add(period);
            format.add(event.getInfo());
            return format.toString();
        }
    }
}
