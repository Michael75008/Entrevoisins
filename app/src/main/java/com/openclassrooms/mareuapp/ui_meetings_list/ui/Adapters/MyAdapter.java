package com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.mareuapp.Events.DeleteMeetingEvent;
import com.openclassrooms.mareuapp.R;
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


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    Context mContext;

    public MyAdapter(Context context, List<Meeting> meetings) {
        this.mContext = context;
        this.mMeetings = meetings;
    }

    public void updateMeeting(List<Meeting> newMeetings) {
        mMeetings.clear();
        mMeetings.addAll(newMeetings);
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
        Meeting meeting = mMeetings.get(position);
        viewHolder.updateWithMeetingItem(this.mMeetings.get(position));

        Glide.with(viewHolder.mMeetingMarker.getContext())
                .load(meeting.getRoom().getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mMeetingMarker);

        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                Toast.makeText(mContext, "La réunion a été supprimée", Toast.LENGTH_SHORT).show();
            }
        });
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
            StringBuilder titleResult = new StringBuilder();
            List<Participant> participants = meeting.getParticipants();
            if (participants != null) {
                for (int i = 0; i < participants.size(); i++) {
                    Participant participant = meeting.getParticipants().get(i);
                    titleResult.append(participant.getMail());
                    if (i < participants.size() - 1) {
                        titleResult.append(", ");
                    }
                }
            }

            this.mMeetingMails.setText(titleResult.toString());
            StringBuilder subTitleResult = new StringBuilder();
            subTitleResult.append(meeting.getName());

            subTitleResult.append(" - ");

            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm", Locale.FRANCE);
            String hour = dateFormat1.format(meeting.getDate());
            subTitleResult.append(hour);

            subTitleResult.append(" - ");

            Room room = meeting.getRoom();
            subTitleResult.append(room.getName());
            this.mMeetingName.setText(subTitleResult.toString());
        }
    }
}
