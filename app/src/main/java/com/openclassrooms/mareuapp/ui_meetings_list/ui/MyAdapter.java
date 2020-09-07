package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MyAdapter(List<Meeting> items) {
      mMeetings = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.updateWithMeetingItem(this.mMeetings.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        public TextView mMeetingName;

        public ViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemview);
        }
        public void updateWithMeetingItem(Meeting meeting){
            this.mMeetingName.setText(meeting.getName());
        }
    }
}
