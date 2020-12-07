package com.openclassrooms.mareuapp.ui_meetings_list.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Room;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomRoomAdapter extends RecyclerView.Adapter<CustomRoomAdapter.MyViewHolder> {

    private Context mContext;
    private List<Room> mRoomList;
    private Room mRoom;
    private int lastSelectedPosition = -1;

    public CustomRoomAdapter(Context context, List<Room> rooms, Room room) {
        this.mContext = context;
        this.mRoomList = rooms;
        this.mRoom = room;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Room room = mRoomList.get(position);
        holder.mRadioButton.setText(room.getName());
        Glide.with(holder.mRoomImage.getContext())
                .load(room.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mRoomImage);
        holder.mRadioButton.setChecked(lastSelectedPosition == position);
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRoom.setName(room.getName());
                mRoom.setImage(room.getImage());
                lastSelectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mRoomList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.room_color)
        ImageView mRoomImage;
        @BindView(R.id.room)
        RadioButton mRadioButton;
        @BindView(R.id.room_view)
        ConstraintLayout mLayout;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
