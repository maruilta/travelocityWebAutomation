package com.automation.pages;

import java.util.Calendar;
import java.util.TimeZone;

public class DatePicker {
    public static String getToday(){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        String todayStr = Integer.toString(todayInt);
        return todayStr;
    }
}
