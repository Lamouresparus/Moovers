package com.moovers.moovers.ui.todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class converter {

    public static String date(String epochSeconds, String formatString) {
        Date updatedate = new Date(Long.parseLong(epochSeconds));
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(updatedate);
    }


    public static Calendar calender(String epochSeconds) {
        Date updatedate = new Date(Long.parseLong(epochSeconds));
        Calendar cal = Calendar.getInstance();
        cal.setTime(updatedate);

        return cal;
    }

}
