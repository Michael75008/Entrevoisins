package com.openclassrooms.mareuapp.Utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

public class Pickers {

    public Pickers() {
    }

    public void showCalendar(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        new DatePickerDialog(context, listener, year, month, day).show();
    }

    public void showTime(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        new TimePickerDialog(context, listener, minute, hour, true).show();
    }
}
