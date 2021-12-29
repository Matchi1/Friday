package fr.umlv.back.formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateFormatterTheadSafe {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm");

    public Date parse(String text) throws ParseException {
        Objects.requireNonNull(text);
        synchronized (dateFormat) {
            return dateFormat.parse(text);
        }
    }

    public String format(Date date) {
        Objects.requireNonNull(date);
        synchronized (dateFormat) {
            return dateFormat.format(date);
        }
    }
}
