package com.openclassrooms.mareuapp.ui_meetings_list.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.events.DeleteMeetingEvent;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.mareuapp.R.string.comma;
import static com.openclassrooms.mareuapp.R.string.dash;
import static com.openclassrooms.mareuapp.R.string.hour_pattern;
import static com.openclassrooms.mareuapp.R.string.room_is_deleted;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Meeting> meetings;
    Context mContext;

    public MyAdapter(Context context, List<Meeting> meetings) {
        this.mContext = context;
        this.meetings = meetings;
    }

    public void updateMeeting(List<Meeting> newMeetings) {
        meetings.clear();
        meetings.addAll(newMeetings);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.fragment_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        Meeting meeting = meetings.get(position);
        viewHolder.updateWithMeetingItem(this.meetings.get(position));
        viewHolder.mColor.setImageResource(meeting.getRoom().getImage());

        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                Toast.makeText(mContext, mContext.getResources().getString(room_is_deleted), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_meeting_name)
        public TextView mName;
        @BindView(R.id.item_meeting_mails)
        public TextView mEmails;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView(R.id.item_logo)
        public ImageView mColor;
        Context mContext;

        public ViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemview);
            mContext = itemview.getContext();
        }

        public void updateWithMeetingItem(Meeting meeting) {
            StringBuilder titleResult = new StringBuilder();
            List<Participant> participants = meeting.getParticipants();
            if (participants != null) {
                for (int i = 0; i < participants.size(); i++) {
                    Participant participant = meeting.getParticipants().get(i);
                    titleResult.append(participant.getMail());
                    if (i < participants.size() - 1) {
                        titleResult.append(mContext.getResources().getString(comma));
                    }
                }
            }
            this.mEmails.setText(titleResult.toString());

            StringBuilder subTitleResult = new StringBuilder();
            subTitleResult.append(meeting.getName());

            subTitleResult.append(mContext.getResources().getString(dash));

            DateFormat dateFormat1 = new SimpleDateFormat(mContext.getResources().getString(hour_pattern), Locale.FRANCE);
            String hour = dateFormat1.format(meeting.getDate());
            subTitleResult.append(hour);

            subTitleResult.append(mContext.getResources().getString(dash));

            Room room = meeting.getRoom();
            subTitleResult.append(room.getName());
            this.mName.setText(subTitleResult.toString());
        }
    }
}
