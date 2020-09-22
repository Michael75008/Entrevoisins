package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.MeetingApiService;
import com.openclassrooms.mareuapp.service.RoomApiService;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;


public class AddMeetingActivity extends AppCompatActivity {


    MeetingApiService mApiService;
    RoomApiService mRoomApiService;
    List<Room> mRooms;
    @BindView(R.id.participants)
    TextInputLayout mEmailsTextInputLayout;
    @BindView(R.id.emails_group)
    ChipGroup mEmailsChipGroup;
    @BindView(R.id.emails)
    TextInputEditText mEmailsTextInputEditText;
    @BindView(R.id.room_name_layout)
    TextInputLayout mRoomNameTextInputLayout;
    @BindView(R.id.room_name)
    AutoCompleteTextView mRoomNameAutoCompleteTextView;
    @BindView(R.id.about_me_layout)
    TextInputLayout mTopicTextInputLayout;
    @BindView(R.id.about_me)
    TextInputEditText mTopicTextInputEditText;
    @BindView(R.id.date)
    TextInputEditText mDateTextInputEditText;
    @BindView(R.id.date_layout)
    TextInputLayout mDateTextInputLayout;
    @BindView(R.id.from)
    TextInputEditText mStartTimeTextInputEditText;
    @BindView(R.id.from_layout)
    TextInputLayout mStartTimeTextInputLayout;
    @BindView(R.id.to_layout)
    TextInputLayout mEndTimeTextInputLayout;
    @BindView(R.id.to)
    TextInputEditText mEndTimeTextInputEditText;



    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();
        mRoomApiService = DI.getRoomApiService();
        mRooms = mRoomApiService.getRooms();
        setActionBar();

        mRoomNameAutoCompleteTextView.setAdapter(new ArrayAdapter<>(
                this, R.layout.room_item, mRooms
        ));
    }

    @OnTouch(R.id.room_name)
    boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomNameAutoCompleteTextView.showDropDown();
            return true;
        }
        return (event.getAction() == MotionEvent.ACTION_UP);
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(this.getApplicationContext(), "L'ajout de réunion à été annulé", Toast.LENGTH_LONG).show();
        return true;
    }
}
