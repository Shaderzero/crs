package ru.ge.util;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Helper {
    public static File dir = new File("C:\\Users");

    public static String getUser() {
        String windowsUser = System.getProperty("user.name");
        return windowsUser;
    }

    public static int getCurrentMonth() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.getTime();
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentYear() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.getTime();
        return calendar.get(Calendar.YEAR);
    }

}
