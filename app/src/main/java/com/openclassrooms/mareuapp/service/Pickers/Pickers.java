package com.openclassrooms.mareuapp.service.Pickers;

import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.openclassrooms.mareuapp.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Pickers extends AppCompatActivity {
    @BindView(R.id.date_image)
    ImageButton mDateButton;
    @BindView(R.id.time_image)
    ImageButton mTimeButton;
    @BindView(R.id.date_view)
    TextView mDateDisplay;
    @BindView(R.id.time_view)
    TextView mTimeDisplay;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int mhour;
    private int mminute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);}

        public void onTimeClick() {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog myTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mTimeDisplay.setText(hourOfDay + " : " + minute);
                }
            }, mHour, mMinute, false);
            myTimePicker.show();
        }
}
