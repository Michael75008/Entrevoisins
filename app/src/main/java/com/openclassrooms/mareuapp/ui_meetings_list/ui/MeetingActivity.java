package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters.MyAdapter;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.mareuapp.R.id.fab_add_meeting;
import static com.openclassrooms.mareuapp.R.id.toolbar;
import static com.openclassrooms.mareuapp.R.layout.activity_list_meeting;

public class MeetingActivity extends AppCompatActivity {

    MeetingApiService mMeetingApiService;
    DatePickerDialog picker;
    @BindView(toolbar)
    Toolbar mToolbar;
    @BindView(fab_add_meeting)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.list_meetings)
    RecyclerView mRecyclerView;


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
        int id = item.getItemId();
        if (id == R.id.filterbydate) {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            picker = new DatePickerDialog(MeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                }
            }, year, month, day);
            picker.show();
            picker.getDatePicker().setMinDate(System.currentTimeMillis());
        } else if (id == R.id.filterbyplace) {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        mMeetingApiService = DI.getMeetingApiService();
        List<Meeting> meetings = mMeetingApiService.getMeetings();
        this.mRecyclerView.setAdapter(new MyAdapter(meetings, this));
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