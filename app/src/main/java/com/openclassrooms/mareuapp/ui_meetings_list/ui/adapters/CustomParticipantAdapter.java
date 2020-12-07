package com.openclassrooms.mareuapp.ui_meetings_list.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomParticipantAdapter extends RecyclerView.Adapter<CustomParticipantAdapter.MyViewHolder> {

    private Context mContext;
    private List<Participant> mParticipantsList;
    private List<Participant> mNewParticipants;


    public CustomParticipantAdapter(Context context, List<Participant> participantList, List<Participant> newParticipants) {
        this.mContext = context;
        this.mParticipantsList = participantList;
        this.mNewParticipants = newParticipants;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participants_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Participant participant = mParticipantsList.get(position);
        holder.ParticipantsName.setText(participant.getMail());
        holder.ParticipantsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.ParticipantsName.isChecked()) {
                    mNewParticipants.add(participant);
                }
            else mNewParticipants.remove(participant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.email)
        CheckBox ParticipantsName;
        @BindView(R.id.view)
        LinearLayout RootView;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
