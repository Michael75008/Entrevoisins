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
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.Utils.Pickers;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.MyAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
    MyAdapter mMyAdapter;
    List<Meeting> mMeetings;
    Pickers mPickers;
    GregorianCalendar c;


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
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, (month + 1));
                    c.set(Calendar.DAY_OF_MONTH, day);
                    mMeetings = mMeetingApiService.getMeetingMatchDate(c);
                    mRecyclerView.setAdapter(new MyAdapter(mMeetings, this));

                });
                return true;


            case R.id.Peach:
                mMeetings = mMeetingApiService.getMeetingsMatchRoomName("Peach");
                mRecyclerView.setAdapter(new MyAdapter(mMeetings, this));
                return true;

            case R.id.Mario:
                mMeetings = mMeetingApiService.getMeetingsMatchRoomName("Mario");
                mRecyclerView.setAdapter(new MyAdapter(mMeetings, this));
                return true;

            case R.id.Luigi:
                mMeetings = mMeetingApiService.getMeetingsMatchRoomName("Luigi");
                mRecyclerView.setAdapter(new MyAdapter(mMeetings, this));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void initData() {
        mMeetingApiService = DI.getMeetingApiService();
        mMeetings = mMeetingApiService.getMeetings();
        mMyAdapter = new MyAdapter(mMeetings, this);
        mRecyclerView.setAdapter(mMyAdapter);
        mPickers = new Pickers();
        c = new GregorianCalendar();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void setActionBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_navigation_icon));
    }
}