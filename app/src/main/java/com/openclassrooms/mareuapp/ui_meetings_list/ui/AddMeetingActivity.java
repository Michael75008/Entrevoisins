package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.Utils.ItemClickSupport;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.model.ValidatorModel;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RecyclerItemSelectedListener;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.service.MyValidator;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomParticipantAdapter;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.CustomRoomAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    Room mRoomValidator;
    Date mDateValidator;
    List<Participant> mParticipantsValidator;
    MyValidator mMyValidator;
    CustomParticipantAdapter recyclerAdapter;
    List<Participant> mParticipantList;
    DatePickerDialog mDatePicker;
    TimePickerDialog mTimePicker;

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
        this.confirgureOnClickRecyclerView();
    }

    @OnTouch(R.id.room_list)
    boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomNameAutoCompleteTextView.showDropDown();
            return true;
        }
        return (event.getAction() == MotionEvent.ACTION_UP);
    }

    @OnClick(R.id.date_image)
    public void onDateClick() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        mDatePicker = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mdate.setText("Date de réunion choisie : " + dayOfMonth + "/" + (mDatePicker.getDatePicker().getMonth() + 1) + "/" + year);
                mDateValidator.setDate(dayOfMonth);
                mDateValidator.setMonth(monthOfYear);
                mDateValidator.setYear(year);
            }
        }, year, month, day);
        mDatePicker.show();
        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
    }

    @OnClick(R.id.time_image)
    public void onTimeClick() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mTime.setText("Heure de réunion choisie :" + selectedHour + ":" + selectedMinute);
                mDateValidator.setHours(selectedHour);
                mDateValidator.setMinutes(selectedMinute);
            }
        }, hour, minute, true);
        mTimePicker.show();
    }

    void initData() {
        mMeeting = new Meeting();
        mRoomApiService = DI.getRoomApiService();
        mParticipantApiService = DI.getParticipantsApiService();
        mApiService = DI.getMeetingApiService();
        mMyValidator = new MyValidator();
        mDateValidator = new Date();
        mParticipantList = mParticipantApiService.getParticipants();
        mParticipantsValidator = new ArrayList<Participant>();
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
                mRoomValidator = mRoomApiService.getRooms().get(position);
                mRoomName.setText(roomChoice);
                mRoomNameAutoCompleteTextView.setText(null);
                mRoomNameAutoCompleteTextView.setHint(null);
            }
        });
    }

    private void setParticipantsAdapters() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerAdapter = new CustomParticipantAdapter(this, mParticipantList);
        mRecyclerView.setAdapter(recyclerAdapter);
        mRecyclerView.setHasFixedSize(true);
        confirgureOnClickRecyclerView();
    }


    @OnClick(R.id.validate_meeting)
    public void meetingValidator() {
        mMeeting = new Meeting(
                666,
                mName.getText().toString(),
                mRoomValidator,
                mDateValidator,
                mParticipantsValidator
        );
        ValidatorModel validatorMessage = mMyValidator.checkMeeting(mMeeting);
        if (!validatorMessage.isValid()) {
            mApiService.createMeeting(mMeeting);
            finish();
            Toast.makeText(getApplicationContext(), "La réunion " + mMeeting.getName() + " a bien étée enregistrée", Toast.LENGTH_LONG).show();
        } else { Toast.makeText(getApplicationContext(), validatorMessage.getErrorMessage(), Toast.LENGTH_LONG).show(); } }

    private void confirgureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.id.participant_recycler_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Participant participant = recyclerAdapter.getUser(position);
                        mParticipantList.remove(participant);
                        recyclerAdapter.notifyDataSetChanged();
                        Chip chip = new Chip(mChipGroup.getContext());
                        chip.setText(participant.getMail().toString());
                        chip.setCloseIconVisible(true);
                        chip.setCheckable(false);
                        chip.setClickable(false);
                        mChipGroup.addView(chip);
                        mChipGroup.setVisibility(View.VISIBLE);
                        mParticipantsValidator.add(participant);

                        chip.setOnCloseIconClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mChipGroup.removeView(view);
                                mParticipantList.add(participant);
                                recyclerAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                });
    }
}

