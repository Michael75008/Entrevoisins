package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.model.ValidatorModel;
import com.openclassrooms.mareuapp.service.apiservice.MeetingApiService;
import com.openclassrooms.mareuapp.service.apiservice.ParticipantApiService;
import com.openclassrooms.mareuapp.service.apiservice.RoomApiService;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.adapters.CustomParticipantAdapter;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.adapters.CustomRoomAdapter;
import com.openclassrooms.mareuapp.utils.MyValidator;
import com.openclassrooms.mareuapp.utils.Pickers;

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

import static com.openclassrooms.mareuapp.R.string.actionbar_add_meeting;
import static com.openclassrooms.mareuapp.R.string.hour_choice;
import static com.openclassrooms.mareuapp.R.string.hour_pattern;
import static com.openclassrooms.mareuapp.R.string.meeting_not_registered;
import static com.openclassrooms.mareuapp.R.string.registered;
import static com.openclassrooms.mareuapp.R.string.the_meeting;
import static com.openclassrooms.mareuapp.R.string.two_points;


public class AddMeetingActivity extends AppCompatActivity {

    RoomApiService mRoomApiService;
    ParticipantApiService mParticipantApiService;
    MeetingApiService mMeetingApiService;
    Room mRoomValidator;
    Date mDateMeeting;
    List<Participant> mParticipantsValidator;
    List<Room> mRoomList;
    MyValidator mValidator;
    CustomParticipantAdapter mParticipantAdapter;
    CustomRoomAdapter mRoomAdapter;
    List<Participant> mParticipantList;
    List<Meeting> mMeetingList;
    Pickers mPickers;
    int uniqueId;

    @BindView(R.id.room_choice)
    TextView mRoomChoice;
    @BindView(R.id.date_view)
    TextView mDate;
    @BindView(R.id.time_view)
    TextView mTime;
    @BindView(R.id.meeting_topic)
    EditText mName;
    @BindView(R.id.participant_recycler_view)
    RecyclerView mParticipantRecyclerView;
    @BindView(R.id.room_list)
    RecyclerView mRoomRecyclerView;

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
            mDate.setText(new StringBuilder()
                    .append(getString(R.string.date_choice)).append(day)
                    .append(getString(R.string.slash)).append(month + 1)
                    .append(getString(R.string.slash))
                    .append(year).toString());
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            mDateMeeting = cal.getTime();
        });
    }

    @OnClick(R.id.time_image)
    public void onTimeClick() {
        mPickers.showTime(this, (timePicker, hours, minutes) -> {
            mTime.setText(new StringBuilder()
                    .append(getString(hour_choice))
                    .append(hours).append(getString(two_points))
                    .append(minutes).toString());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mDateMeeting);
            calendar.set(Calendar.MINUTE, minutes);
            calendar.set(Calendar.HOUR_OF_DAY, hours);
            mDateMeeting = calendar.getTime();
        });
    }

    void initData() {
        mMeetingApiService = DI.getMeetingApiService();
        mMeetingList = mMeetingApiService.getMeetings();
        mRoomApiService = DI.getRoomApiService();
        mRoomList = mRoomApiService.getRooms();
        mParticipantApiService = DI.getParticipantService();
        mParticipantList = mParticipantApiService.getParticipants();
        mValidator = new MyValidator();
        mParticipantsValidator = new ArrayList<>();
        mDateMeeting = Calendar.getInstance().getTime();
        mRoomValidator = new Room();
        mPickers = new Pickers();
        uniqueId = mMeetingList.size();
    }

    private void setActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(actionbar_add_meeting);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(getApplicationContext(), meeting_not_registered, Toast.LENGTH_SHORT).show();
        return true;
    }

    public void onSupportDateAndTime() {
        //Todo : remove this comment
        // dans une activity tu as deja le context donc tu peux directement avoir des ressources => directement getString, getInt etc .
        mDate.setText(new SimpleDateFormat(getString(R.string.day_pattern), Locale.FRANCE).format(new Date()));
        mTime.setText(new SimpleDateFormat(getString(hour_pattern), Locale.FRANCE).format(new Date()));
    }

    private void setRoomsAdapter() {
        mRoomRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRoomAdapter = new CustomRoomAdapter(AddMeetingActivity.this, mRoomList, mRoomValidator);
        mRoomRecyclerView.setAdapter(mRoomAdapter);
    }

    private void setParticipantsAdapters() {
        mParticipantRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mParticipantAdapter = new CustomParticipantAdapter(this, mParticipantList, mParticipantsValidator);
        mParticipantRecyclerView.setAdapter(mParticipantAdapter);
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
        ValidatorModel validatorMessage = mValidator.checkMeeting(mMeeting);
        if (validatorMessage.isValid()) {
            if (mMeetingApiService.isMeetingAlreadyCreated(mMeeting)){
                Toast.makeText(getApplicationContext(), getString(R.string.cannot_create_same_meeting_twice), Toast.LENGTH_SHORT).show();
            }else {
                mMeetingApiService.createMeeting(mMeeting);
                finish();
                Toast.makeText(getApplicationContext(), getString(the_meeting) + mMeeting.getName() + getString(registered), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getString(validatorMessage.getErrorMessage()), Toast.LENGTH_SHORT).show();
        }
    }
}

