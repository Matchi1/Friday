package fr.umlv.back.date;

import fr.umlv.back.DateDetails;
import fr.umlv.back.formatter.DateFormatterTheadSafe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class DateFormatterTest {
    /**
     * Create Date according to the specified date details
     *
     * @param details the specified date details
     */
    private static Date createDateFromDateDetails(DateDetails details) {
        var calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(details.year(), details.month(), details.day(), details.hour(), details.minute());
        return calendar.getTime();
    }

    @Test
    public void formatDateTest() {
        /* Date : 01 January 2000, 00:00 AM */
        var date = createDateFromDateDetails(new DateDetails(2000, 0, 1, 0, 0));
        var dateFormatter = new DateFormatterTheadSafe();
        Assertions.assertEquals("20000101T0000", dateFormatter.format(date));
    }
}