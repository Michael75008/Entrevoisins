package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.Events.DeleteMeetingEvent;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.Utils.Pickers;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.MyAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    List<Meeting> mMeetings;
    RoomApiService mRoomApiService;
    MyAdapter mMyAdapter;
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
                initData();
                return true;

            case R.id.filterbydate:
                mPickers.showCalendar(this, (datePicker, year, month, day) -> {
                    mCalendar.set(year, month, day);
                    mDate = mCalendar.getTime();
                    mMyAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchDate(mDate));
                });
                return true;

            case R.id.Peach:
                mMyAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchRoom(mRoomApiService.getRooms().get(0)));
                return true;

            case R.id.Mario:
                mMyAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchRoom(mRoomApiService.getRooms().get(1)));
                return true;

            case R.id.Luigi:
                mMyAdapter.updateMeeting(mMeetingApiService.getMeetingsMatchRoom(mRoomApiService.getRooms().get(2)));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void initData() {
        mMeetingApiService = DI.getMeetingApiService();
        mRoomApiService = DI.getRoomApiService();
        mMeetings = mMeetingApiService.getMeetings();
        mMyAdapter = new MyAdapter(this, mMeetings);
        mRecyclerView.setAdapter(mMyAdapter);
        mPickers = new Pickers();
        mCalendar = Calendar.getInstance();
        mDate = new Date();
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        initData();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        initData();
    }

    private void setActionBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_navigation_icon));
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mMeetingApiService.deleteMeeting(event.meeting);
        initData();
    }
}
