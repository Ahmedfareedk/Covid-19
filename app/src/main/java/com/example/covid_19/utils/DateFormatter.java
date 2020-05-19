package com.example.covid_19.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
    public static String dateFormatter(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static String monthName(int day, int month, int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
        return dateFormat.format(calendar.getTime());
    }

    public static String formateResponsedDate(String date){
        SimpleDateFormat responsedDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateFormatter = responsedDateFormatter.parse(date);
            responsedDateFormatter = new SimpleDateFormat("MMMM dd yyyy");
           // assert dateFormatter != null;
            date = responsedDateFormatter.format(dateFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}