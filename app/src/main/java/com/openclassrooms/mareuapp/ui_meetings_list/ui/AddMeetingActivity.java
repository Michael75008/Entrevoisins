package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.service.MyValidator;
import com.openclassrooms.mareuapp.service.Pickers.Pickers;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomRoomAdapter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;


public class AddMeetingActivity extends AppCompatActivity {

    RoomApiService mRoomApiService;
    ParticipantApiService mParticipantApiService;
    MeetingApiService mApiService;
    Meeting mMeeting;
    MyValidator mMyValidator;
    Pickers mPickers;


    @BindView(R.id.room_list)
    AutoCompleteTextView mRoomNameAutoCompleteTextView;
    @BindView(R.id.participants_list)
    MultiAutoCompleteTextView mParticipantsNameAutoCompleteTextView;
    @BindView(R.id.date_view)
    TextView mdate;
    @BindView(R.id.time_view)
    TextView mTime;
    @BindView(R.id.meeting_topic)
    EditText mMeetingName;
    @BindView(R.id.room_item)
    TextView mRoomName;
    @BindView(R.id.participant_item)
    TextView mParticipantsName;



    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        initData();
        setActionBar();
        setAdapters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }


    @OnTouch(R.id.participants_list)
    boolean onTouch2(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mParticipantsNameAutoCompleteTextView.showDropDown();
            return true;
        }
        return (event.getAction() == MotionEvent.ACTION_UP);
    }

    @OnTouch(R.id.room_list)
    boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomNameAutoCompleteTextView.showDropDown();
            return true;
        }
        return (event.getAction() == MotionEvent.ACTION_UP);

    }


    @OnClick(R.id.time_image)
    public void onTimeClick() {
        mPickers.onTimeClick();
    }

    void initData() {
        mMeeting = new Meeting();
        mRoomApiService = DI.getRoomApiService();
        mParticipantApiService = DI.getParticipantsApiService();
        mApiService = DI.getMeetingApiService();
        mMyValidator = new MyValidator(this, mMeeting);
        mPickers = new Pickers();
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ma Réu - Ajout de Réunion");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(getApplicationContext(), "Réunion non enregistrée", Toast.LENGTH_LONG).show();
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setAdapters() {

        CustomRoomAdapter customRoomAdapter = new CustomRoomAdapter(this, mRoomApiService.getRooms());

        mRoomNameAutoCompleteTextView.setAdapter(customRoomAdapter);

        mParticipantsNameAutoCompleteTextView.setAdapter(new ArrayAdapter<>(
                this, R.layout.participants_item, R.id.participant_mail, mParticipantApiService.getParticipantsByMail()));
        

        mRoomNameAutoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                String roomChoice = mRoomApiService.getRooms().get(position).getName();
                mRoomName.setText(roomChoice);
                mRoomNameAutoCompleteTextView.setText(null);
                mRoomNameAutoCompleteTextView.setHint(null);
            }
        });

    }

    @OnClick(R.id.validate_meeting)
    public void meetingValidator() {
        mApiService.createMeeting(mMeeting);
        finish();
        Toast.makeText(getApplicationContext(), "La réunion " + mMeeting.getName() + " a bien étée enregistrée", Toast.LENGTH_LONG).show();
    }
}

