package fr.umlv.back.formatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateFormatterThreadSafeTest {
    @Test
    public void formatDateTest() {
        /* Date : 01 January 2000, 00:00 AM */
        var date = "2000/01/01 00:00))";
        var dateFormatter = new DateFormatter();
        Assertions.assertEquals("20000101T0000", dateFormatter.format(date));
    }
}