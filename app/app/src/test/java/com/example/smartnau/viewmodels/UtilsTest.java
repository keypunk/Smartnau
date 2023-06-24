package com.example.smartnau.viewmodels;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {
    Utils utils = new Utils();

    public void testFormatDateTime() {
        Long input = 1687633072462L;

        String actual = Utils.formatDateTime(input);
        String expected = "24.06.23 20:57";

        assertEquals(expected,actual);
    }

    public void testFormatSurveyResponse() {
        String expected = "Ja: 1\nNein: 2";
        String actual = Utils.formatSurveyResponse("1,2");

        assertEquals(expected,actual);
    }
}