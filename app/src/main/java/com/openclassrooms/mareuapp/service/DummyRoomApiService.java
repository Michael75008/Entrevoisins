package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Room;

import java.util.List;

public class DummyRoomApiService {

    private final List<Room> mRooms = RoomApiServiceGenerator.generateRooms();

    /**
     * Get Room's List
     */

    public List<Room> getRooms() {
        return mRooms;
    }

}
