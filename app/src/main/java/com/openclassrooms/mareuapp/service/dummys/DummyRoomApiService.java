package com.openclassrooms.mareuapp.service.dummys;

import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.apiservice.RoomApiService;
import com.openclassrooms.mareuapp.service.generators.RoomApiServiceGenerator;

import java.util.List;

public class DummyRoomApiService implements RoomApiService {

    private final List<Room> mRooms = RoomApiServiceGenerator.generateRooms();

    /**
     * Get Room's List
     */

    public List<Room> getRooms() {
        return mRooms;
    }
}
