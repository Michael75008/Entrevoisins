package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.DummyMeetingApiServiceGenerator;
import com.openclassrooms.mareuapp.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 */
@SuppressLint("ValidFragment")
public class MainFragment extends AppCompatActivity {

    private MeetingApiService mApiService;

    @BindView(R.id.list_meetings)
    RecyclerView mRecyclerView;
    private List<Meeting> meetings;

    @SuppressLint("ValidFragment")
    public MainFragment(MeetingApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);
        mApiService.getMeetings();
        meetings = DummyMeetingApiServiceGenerator.generateMeetings();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_item_list, container, false);
        mRecyclerView = (RecyclerView) view;
        MyAdapter adapter = new MyAdapter(meetings);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        return view;
    }



    private void initList(){
        List<Meeting> meetings = new ArrayList<>();
        meetings = mApiService.getMeetings();
        mRecyclerView.setAdapter(new MyAdapter(meetings));
    }
}