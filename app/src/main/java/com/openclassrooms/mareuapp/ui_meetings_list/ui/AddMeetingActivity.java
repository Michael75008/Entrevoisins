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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RecyclerItemSelectedListener;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.service.MyValidator;
import com.openclassrooms.mareuapp.service.Pickers.Pickers;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomParticipantAdapter;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomRoomAdapter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;


public class AddMeetingActivity extends AppCompatActivity implements RecyclerItemSelectedListener, View.OnClickListener {

    RoomApiService mRoomApiService;
    ParticipantApiService mParticipantApiService;
    MeetingApiService mApiService;
    Meeting mMeeting;
    MyValidator mMyValidator;
    Pickers mPickers;
    CustomParticipantAdapter recyclerAdapter;
    List<String> mParticipantList;


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
    @BindView(R.id.participants_list)
    ChipGroup mChipGroup;


    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        initData();
        setActionBar();
        setRoomsAdapter();
        setParticipantsAdapters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
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
        mParticipantList = mParticipantApiService.getParticipantsByMail();
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

    private void setRoomsAdapter() {
        CustomRoomAdapter customRoomAdapter = new CustomRoomAdapter(this, mRoomApiService.getRooms());
        mRoomNameAutoCompleteTextView.setAdapter(customRoomAdapter);
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

    private void setParticipantsAdapters() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
        recyclerAdapter = new CustomParticipantAdapter(this, mParticipantList);
        mRecyclerView.setAdapter(recyclerAdapter);
    }


    @OnClick(R.id.validate_meeting)
    public void meetingValidator() {
        mApiService.createMeeting(mMeeting);
        finish();
        Toast.makeText(getApplicationContext(), "La réunion " + mMeeting.getName() + " a bien étée enregistrée", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(String participant) {
        Chip chip = new Chip(this);
        chip.setText(participant);
        chip.setCloseIconVisible(true);
        chip.setCheckable(false);
        chip.setClickable(false);
        chip.setOnCloseIconClickListener(this);
        mChipGroup.addView(chip);
        mChipGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        Chip chip = (Chip) view;
        mChipGroup.removeView(chip);
    }
}

