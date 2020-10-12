package com.openclassrooms.mareuapp.service.Pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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
        ButterKnife.bind(this);



        //Pick time's click event listener
        mTimeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            }
        });

        //PickDate's click event listener
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mhour = c.get(Calendar.HOUR_OF_DAY);
        mminute = c.get(Calendar.MINUTE);
    }

}
