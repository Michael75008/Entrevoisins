package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.events.DeleteMeetingEvent;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MyAdapter(List<Meeting> items) {
        this.mMeetings = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.fragment_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Meeting meeting = mMeetings.get(position);
        viewHolder.updateWithMeetingItem(this.mMeetings.get(position));

        Glide.with(viewHolder.mMeetingMarker.getContext())
                .load(R.drawable.ic_circle_red)
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mMeetingMarker);

        viewHolder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteMeetingEvent(meeting)));
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_meeting_name)
        public TextView mMeetingName;
        @BindView(R.id.item_meeting_mails)
        public TextView mMeetingMails;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.item_logo)
        public ImageView mMeetingMarker;

        public ViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemview);
        }

        public void updateWithMeetingItem(Meeting meeting) {
            StringBuilder result = new StringBuilder();
            List<Participant> participants = meeting.getParticipants();
            if (participants != null) {
                for (int i = 0; i < participants.size(); i++) {
                    Participant participant = meeting.getParticipants().get(i);
                    result.append(participant.getMail());
                    result.append(", ");
                }
            }
            this.mMeetingMails.setText(result.toString());

            StringBuilder result1 = new StringBuilder();
            result1.append(meeting.getName());
            result1.append(" - ");
            result1.append(meeting.getDate());
            if (meeting.getDate() != null) {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                String dateformatted = dateFormat.format(meeting.getDate());
                result1.append(dateformatted);
            }
            result1.append(" - ");
            result1.append(meeting.getRoom());

            this.mMeetingName.setText(result1.toString());
        }

        private Calendar dateToCalendar(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
    }
}
