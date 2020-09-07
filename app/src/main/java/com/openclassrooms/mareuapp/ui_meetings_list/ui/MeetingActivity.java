package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.DummyMeetingApiServiceGenerator;
import com.openclassrooms.mareuapp.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.mareuapp.R.id.fab_add_meeting;
import static com.openclassrooms.mareuapp.R.id.toolbar;
import static com.openclassrooms.mareuapp.R.layout.activity_list_meeting;

public class MeetingActivity extends AppCompatActivity {

    private MeetingApiService mApiService;
   private List<Meeting> mMeeting = new ArrayList<Meeting>();


    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(fab_add_meeting)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.list_meetings)
    RecyclerView mRecyclerView;

    public MeetingActivity(MeetingApiService apiService) {
        mApiService = apiService;
    }
    public MeetingActivity() {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_list_meeting);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(Objects.requireNonNull(getDrawable(R.drawable.ic_navigation_icon)));
        this.configureRecyclerView();

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeetingActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.filterbydate) {
            return true;
        } else if (id == R.id.filterbyplace) {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
    private void configureRecyclerView(){
        mMeeting = DummyMeetingApiServiceGenerator.generateMeetings();
        this.mRecyclerView.setAdapter( new MyAdapter(mMeeting));
    }
}