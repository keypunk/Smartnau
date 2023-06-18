package com.example.smartnau.viewmodels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    // Calendar Klasse
    public static String formatDateTime(long dateInMillisecond){
        String formatPattern = "dd.MM.yy HH:mm";
        SimpleDateFormat MillisecondToFormatPattern = new SimpleDateFormat(formatPattern,
                Locale.GERMANY);

        String datePattern = MillisecondToFormatPattern.format(new Date(dateInMillisecond));

        return datePattern;
    }
}
