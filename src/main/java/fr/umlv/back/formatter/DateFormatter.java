package fr.umlv.back.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateFormatter {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss");

    public LocalDate formatFromStringToDate(String date) {
        Objects.requireNonNull(date);
        return LocalDate.parse(date, formatter);
    }

    public String formatFromDateToString(LocalDate date) {
        Objects.requireNonNull(date);
        return date.format(formatter);
    }

    public String format(String date) {
        Objects.requireNonNull(date);
        return LocalDate.parse(date).format(formatter);
    }
}
