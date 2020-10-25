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
import com.openclassrooms.mareuapp.service.ApiServices.RecyclerItemSelectedListener;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.AddMeetingActivity;

import java.util.List;

public class CustomParticipantAdapter extends RecyclerView.Adapter<CustomParticipantAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mParticipantsList;
    private RecyclerItemSelectedListener mRecyclerItemSelectedListener;

    public CustomParticipantAdapter(Context context, List<String> participantList) {
        this.mContext = context;
        this.mParticipantsList = participantList;
        mRecyclerItemSelectedListener = (AddMeetingActivity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.participants_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.ParticipantsName.setText(mParticipantsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ParticipantsName;
        LinearLayout RootView;

        public MyViewHolder(View view) {
            super(view);
            ParticipantsName = view.findViewById(R.id.participant_mail);
            RootView = view.findViewById(R.id.participant_list_linear_layout);
            RootView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mRecyclerItemSelectedListener.onItemSelected(mParticipantsList.get(getAdapterPosition()));
            mParticipantsList.remove(mParticipantsList.get(getAdapterPosition()));
        }
    }


}
