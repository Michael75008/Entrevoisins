package com.openclassrooms.mareuapp.service.Dummys;

import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.Generators.RoomApiServiceGenerator;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;

import java.util.ArrayList;
import java.util.List;

public class DummyRoomApiService implements RoomApiService {

    private final List<Room> mRooms = RoomApiServiceGenerator.generateRooms();

    /**
     * Get Room's List
     */

    public List<Room> getRooms() {
        return mRooms;
    }

    public List<String> getRoomsByName() {
        List<String> currentRooms = new ArrayList<>();
        for (int i = 0; i < mRooms.size(); i++) {
            String room = mRooms.get(i).getName();
            if (room != null) {
                currentRooms.add(room);
            }
        }
        return currentRooms;
    }
}
