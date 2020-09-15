package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.openclassrooms.mareuapp.R;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddMeetingActivity extends AppCompatActivity {
    DatePickerDialog mDatePickerDialogpicker;
    TimePickerDialog mTimePickerDialog;
    @BindView(R.id.date_result)
    TextView mDateTextView;
    @BindView(R.id.date_editor)
    EditText mDateInput;
    @BindView(R.id.date_validator)
    Button mDateButton;
    @BindView(R.id.time_editor)
    EditText mTimeInput;
    @BindView(R.id.time_result)
    TextView mTimeTextView;
    @BindView(R.id.time_validator)
    Button mTimeButton;



    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_add_meeting);
        setActionBar();
        ButterKnife.bind(this);
        setDataInput();
        setHourInput();
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setDataInput() {
        mDateInput.setInputType(InputType.TYPE_NULL);
        mDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                mDatePickerDialogpicker = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mDateInput.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/ " + year);

                    }
                }, year, month, day);
                mDatePickerDialogpicker.show();
            }
        });
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            public void onClick(View v){
                mDateTextView.setText("Date de Réunion: " + mDateInput.getText());
            }
        });
    }

    private void setHourInput(){
        mTimeInput.setInputType(InputType.TYPE_NULL);
        mTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr1 = Calendar.getInstance();
                int hour = cldr1.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr1.get(Calendar.MINUTE);

                mTimePickerDialog = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        mTimeInput.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
                mTimePickerDialog.show();
            }
        });
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeTextView.setText("Heure de réunion: " + mTimeInput.getText());
            }
        });
    }
}
