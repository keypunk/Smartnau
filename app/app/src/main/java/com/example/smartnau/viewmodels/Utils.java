package com.example.smartnau.viewmodels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String formatDateTime(long dateInMillisecond){
        String formatPattern = "dd.MM.yy HH:mm";
        SimpleDateFormat MillisecondToFormatPattern = new SimpleDateFormat(formatPattern,
                Locale.GERMANY);

        String datePattern = MillisecondToFormatPattern.format(new Date(dateInMillisecond));

        return datePattern;
    }

    public static String formatSurveyResponse(String surveryAnswer) {
        String[] yesno = surveryAnswer.split(",");
        String yes = yesno[0];
        String no = yesno[1];
        return "Ja: " + yes + "\n" + "Nein: " + no;
    }
}
