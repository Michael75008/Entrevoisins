package com.openclassrooms.mareuapp.service.Pickers;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

public class CreateHourPicker {
    TimePickerDialog mTimePickerDialog;

    public interface onTimeSet {
        public void onTime(TimePicker view, int hourOfDay, int minute);
    }

    onTimeSet mOnTimeSet;

    public void setTimeListener(onTimeSet onTimeSet) {
        mOnTimeSet = onTimeSet;
    }

    public CreateHourPicker(Context ctx) {
        mTimePickerDialog = new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mOnTimeSet.onTime(view, hourOfDay, minute);
            }
        }, 1, 1, true);
    }

    public void show() {
        mTimePickerDialog.show();
    }
}
