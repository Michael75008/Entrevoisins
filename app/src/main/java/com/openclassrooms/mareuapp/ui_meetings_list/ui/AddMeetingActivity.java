package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.Utils.MyValidator;
import com.openclassrooms.mareuapp.Utils.Pickers;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.model.ValidatorModel;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomParticipantAdapter;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomRoomAdapter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;


public class AddMeetingActivity extends AppCompatActivity {

    RoomApiService mRoomApiService;
    ParticipantApiService mParticipantApiService;
    MeetingApiService mMeetingApiService;
    Meeting mMeeting;
    Room mRoomValidator;
    GregorianCalendar mDateValidator;
    List<Participant> mParticipantsValidator;
    MyValidator mMyValidator;
    CustomParticipantAdapter recyclerAdapter;
    List<Participant> mParticipantList;
    Pickers mPickers;
    int uniqueId = 3;



    @BindView(R.id.room_list)
    AutoCompleteTextView mRoomNameAutoCompleteTextView;
    @BindView(R.id.date_view)
    TextView mdate;
    @BindView(R.id.time_view)
    TextView mTime;
    @BindView(R.id.meeting_topic)
    EditText mName;
    @BindView(R.id.room_item)
    TextView mRoomName;
    @BindView(R.id.participant_recycler_view)
    RecyclerView mRecyclerView;


    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        initData();
        setActionBar();
        setParticipantsAdapters();
    }

    public int getUniqueId()
    {
        return uniqueId++;
    }

    @OnTouch(R.id.room_list)
    boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomNameAutoCompleteTextView.showDropDown();
            setRoomsAdapter();
            return true;
        }
        return (event.getAction() == MotionEvent.ACTION_UP);
    }

    @OnClick(R.id.date_image)
    public void onDateClick() {
        mPickers.showCalendar(this, (datePicker, year, month, day) -> {
            mdate.setText("Date de réunion choisie : " + day + "/" + (month + 1) + "/" + year);
            mDateValidator.set(year, (month+1), day);
        });
    }

    @OnClick(R.id.time_image)
    public void onTimeClick() {
        mPickers.showTime(this, (timePicker, hours, minutes) -> {
            mTime.setText("Heure de réunion choisie :" + hours + ":" + minutes);
            mDateValidator.set(Calendar.HOUR_OF_DAY, hours);
            mDateValidator.set(Calendar.MINUTE, minutes);
        });
    }

    void initData() {
        mMeeting = new Meeting();
        mRoomApiService = DI.getRoomApiService();
        mParticipantApiService = DI.getParticipantsApiService();
        mMeetingApiService = DI.getMeetingApiService();
        mMyValidator = new MyValidator();
        mDateValidator = new GregorianCalendar();
        mParticipantList = mParticipantApiService.getParticipants();
        mParticipantsValidator = new ArrayList<>();
        mPickers = new Pickers();
        mdate.setText(LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());
        mTime.setText(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute());
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ma Réu - Ajout de Réunion");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(getApplicationContext(), "Réunion non enregistrée", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void setRoomsAdapter() {
        CustomRoomAdapter customRoomAdapter = new CustomRoomAdapter(this, mRoomApiService.getRooms());
        mRoomNameAutoCompleteTextView.setAdapter(customRoomAdapter);
        mRoomNameAutoCompleteTextView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                mRoomValidator = mRoomApiService.getRooms().get(position);
                mRoomNameAutoCompleteTextView.setText(mRoomValidator.getName());
            }
        });
    }

    private void setParticipantsAdapters() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerAdapter = new CustomParticipantAdapter(this, mParticipantList, mParticipantsValidator);
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    @OnClick(R.id.validate_meeting)
    public void meetingValidator() {
        mMeeting = new Meeting(
                getUniqueId(),
                mName.getText().toString(),
                mRoomValidator,
                mDateValidator,
                mParticipantsValidator
        );
        ValidatorModel validatorMessage = mMyValidator.checkMeeting(mMeeting);
        if (validatorMessage.isValid()) {
            mMeetingApiService.createMeeting(mMeeting);
            finish();
            Toast.makeText(getApplicationContext(), "La réunion " + mMeeting.getName() + " a bien étée enregistrée", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), validatorMessage.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

