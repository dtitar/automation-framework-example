package com.github.dtitar.core.helpers;

import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {

    public static String formatCurrentDate(String ofPattern) {
        return ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern(ofPattern));
    }

    public static String getCurrentDateMinusYears(String ofPattern, int years) {
        return ZonedDateTime.now()
                .minus(Period.ofYears(years))
                .format(DateTimeFormatter.ofPattern(ofPattern));
    }
}