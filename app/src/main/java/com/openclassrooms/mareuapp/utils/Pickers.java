package com.openclassrooms.mareuapp.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

public class Pickers {

    public Pickers() {
    }

    public void showCalendar(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar mDate = (Calendar) Calendar.getInstance();
        int day = mDate.get(Calendar.DAY_OF_MONTH);
        int month = mDate.get(Calendar.MONTH);
        int year = mDate.get(Calendar.YEAR);

        new DatePickerDialog(context, listener, year, month, day).show();
    }

    public void showTime(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar mTime = (Calendar) Calendar.getInstance();
        int hour = mTime.get(Calendar.HOUR_OF_DAY);
        int minute = mTime.get(Calendar.MINUTE);

        new TimePickerDialog(context, listener, minute, hour, true).show();
    }
}
