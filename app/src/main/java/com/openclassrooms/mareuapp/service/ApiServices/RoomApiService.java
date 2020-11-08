package com.openclassrooms.mareuapp.service.ApiServices;

import com.openclassrooms.mareuapp.model.Room;

import java.util.List;

public interface RoomApiService {

    /**
     * Get all my Rooms
     */

    List<Room> getRooms();
}
