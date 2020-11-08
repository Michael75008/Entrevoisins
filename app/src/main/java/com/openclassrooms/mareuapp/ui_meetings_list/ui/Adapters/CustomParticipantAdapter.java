package com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

public class CustomParticipantAdapter extends RecyclerView.Adapter<CustomParticipantAdapter.MyViewHolder> {

    private Context mContext;
    private List<Participant> mParticipantsList;


    public CustomParticipantAdapter(Context context, List<Participant> participantList) {
        this.mContext = context;
        this.mParticipantsList = participantList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participants_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String mName = mParticipantsList.get(position).getMail();
        holder.ParticipantsName.setText(mName);
    }

    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ParticipantsName;
        LinearLayout RootView;

        public MyViewHolder(View view) {
            super(view);
            ParticipantsName = view.findViewById(R.id.participant_mail);
            RootView = view.findViewById(R.id.participant_list_linear_layout);
        }
    }

    public Participant getUser(int position) {
        return mParticipantsList.get(position);
    }
}
