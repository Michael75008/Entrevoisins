package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.openclassrooms.mareuapp.R.string.ActionBarAddMeeting;
import static com.openclassrooms.mareuapp.R.string.RéuNotRegistred;
import static com.openclassrooms.mareuapp.R.string.choosenDate;
import static com.openclassrooms.mareuapp.R.string.choosenHour;
import static com.openclassrooms.mareuapp.R.string.colon;
import static com.openclassrooms.mareuapp.R.string.slash;


public class AddMeetingActivity extends AppCompatActivity {

    RoomApiService mRoomApiService;
    ParticipantApiService mParticipantApiService;
    MeetingApiService mMeetingApiService;
    Room mRoomValidator;
    Calendar mDateCalendar;
    Date mDateMeeting;
    List<Participant> mParticipantsValidator;
    List<Room> mRoomList;
    MyValidator mMyValidator;
    CustomParticipantAdapter recyclerAdapter;
    CustomRoomAdapter mRoomAdapter;
    List<Participant> mParticipantList;
    List<Meeting> mMeetingList;
    Pickers mPickers;
    int uniqueId;

    @BindView(R.id.room_choice)
    TextView mRoomChoice;
    @BindView(R.id.date_view)
    TextView mdate;
    @BindView(R.id.time_view)
    TextView mTime;
    @BindView(R.id.meeting_topic)
    EditText mName;
    @BindView(R.id.participant_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.room_list)
    RecyclerView mRecyclerView2;

    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        initData();
        setActionBar();
        setParticipantsAdapters();
        setRoomsAdapter();
        onSupportDateAndTime();
    }

    public int getUniqueId() {
        return uniqueId++;
    }

    @OnClick(R.id.date_image)
    public void onDateClick() {
        mPickers.showCalendar(this, (datePicker, year, month, day) -> {
            mdate.setText(choosenDate + day + slash + (month + 1) + slash + year);
            mDateCalendar.set(year, month, day);
        });
    }

    @OnClick(R.id.time_image)
    public void onTimeClick() {
        mPickers.showTime(this, (timePicker, hours, minutes) -> {
            mTime.setText(choosenHour + hours + colon + minutes);
            mDateCalendar.set(Calendar.HOUR_OF_DAY, hours);
            mDateCalendar.set(Calendar.MINUTE, minutes);
        });
    }

    void initData() {
        mMeetingApiService = DI.getMeetingApiService();
        mMeetingList = mMeetingApiService.getMeetings();
        mRoomApiService = DI.getRoomApiService();
        mRoomList = mRoomApiService.getRooms();
        mParticipantApiService = DI.getParticipantService();
        mParticipantList = mParticipantApiService.getParticipants();
        mMyValidator = new MyValidator();
        mDateCalendar = Calendar.getInstance();
        mDateMeeting = mDateCalendar.getTime();
        mParticipantsValidator = new ArrayList<>();
        mRoomValidator = new Room();
        mPickers = new Pickers();
        uniqueId = mMeetingList.size();
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ActionBarAddMeeting);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(getApplicationContext(), RéuNotRegistred, Toast.LENGTH_SHORT).show();
        return true;
    }

    public void onSupportDateAndTime() {
        mdate.setText(new SimpleDateFormat(getString(R.string.ddMMyyyyPatern), Locale.FRANCE).format(new Date()));
        mTime.setText(new SimpleDateFormat(getString(R.string.HHmmPatern), Locale.FRANCE).format(new Date()));
    }

    private void setRoomsAdapter() {
        mRecyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        mRoomAdapter = new CustomRoomAdapter(AddMeetingActivity.this, mRoomList, mRoomValidator);
        mRecyclerView2.setAdapter(mRoomAdapter);
    }

    private void setParticipantsAdapters() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerAdapter = new CustomParticipantAdapter(this, mParticipantList, mParticipantsValidator);
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    @OnClick(R.id.validate_meeting)
    public void meetingValidator() {
        Meeting mMeeting = new Meeting(
                getUniqueId(),
                mName.getText().toString(),
                mRoomValidator,
                mDateMeeting,
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

