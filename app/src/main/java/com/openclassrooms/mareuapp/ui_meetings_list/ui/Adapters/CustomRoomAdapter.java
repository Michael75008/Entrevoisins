package com.openclassrooms.mareuapp.ui_meetings_list.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Room;

import java.util.List;

public class CustomRoomAdapter extends ArrayAdapter<Room> {

    private final List<Room> mRoomList;

    private LayoutInflater mLayoutInflater;


    public CustomRoomAdapter(Context context, List<Room> rooms) {
        super(context, 0, rooms);
        this.mRoomList = rooms;
        this.mLayoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Room getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.room_item, parent, false);
            TextView mRoomName = convertView.findViewById(R.id.room_name);
            ImageView mRoomColor = convertView.findViewById(R.id.room_color);
            Room room = mRoomList.get(position);
            if (room != null) {
                mRoomName.setText(room.getName());
                mRoomColor.setImageResource(room.getImage());
            }
        }
        return convertView;
    }
}
