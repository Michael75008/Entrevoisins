package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.events.DeleteMeetingEvent;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.apiservice.MeetingApiService;
import com.openclassrooms.mareuapp.service.apiservice.RoomApiService;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.adapters.MyAdapter;
import com.openclassrooms.mareuapp.utils.Pickers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.mareuapp.R.id.fab_add_meeting;
import static com.openclassrooms.mareuapp.R.id.toolbar;
import static com.openclassrooms.mareuapp.R.layout.activity_list_meeting;

public class MeetingActivity extends AppCompatActivity {

    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(fab_add_meeting)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.list_meetings)
    RecyclerView mRecyclerView;

    MeetingApiService mMeetingApiService;
    RoomApiService mRoomApiService;
    List<Meeting> mMeetings;
    MyAdapter mAdapter;
    Pickers mPickers;
    Calendar mCalendar;
    Date mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_list_meeting);
        ButterKnife.bind(this);
        setActionBar();
        initData();

        mFloatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MeetingActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getallmeetings:
                mAdapter.updateMeeting(mMeetingApiService.getMeetings());
                utilsForFilter(R.string.empty_full_list);
                return true;

            case R.id.filterbydate:
                mPickers.showCalendar(this, (datePicker, year, month, day) -> {
                    mCalendar.set(year, month, day);
                    mDate = mCalendar.getTime();
                    mAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchDate(mDate));
                    utilsForFilter(R.string.empty_date);
                });
                return true;

            case R.id.Peach:
                mAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchRoom(mRoomApiService.getRooms().get(0)));
                utilsForFilter(R.string.empty_room);
                return true;

            case R.id.Mario:
                mAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchRoom(mRoomApiService.getRooms().get(1)));
                utilsForFilter(R.string.empty_room);
                return true;

            case R.id.Luigi:
                mAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchRoom(mRoomApiService.getRooms().get(2)));
                utilsForFilter(R.string.empty_room);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void initData() {
        mMeetingApiService = DI.getMeetingApiService();
        mRoomApiService = DI.getRoomApiService();
        mPickers = new Pickers();
        mCalendar = Calendar.getInstance();
        mDate = new Date();
        mMeetings = new ArrayList<>();
        mAdapter = new MyAdapter(this, mMeetings);
        mRecyclerView.setAdapter(mAdapter);
    }

    void utilsForFilter(int message) {
        if (mMeetings.isEmpty())
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.updateMeeting(mMeetingApiService.getMeetings());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void setActionBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_navigation_icon));
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mMeetingApiService.deleteMeeting(event.meeting);
        mAdapter.updateMeeting(mMeetingApiService.getMeetings());
    }
}
