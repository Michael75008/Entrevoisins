package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Room;

import java.util.List;

public interface RoomApiService {

    /**
     * Get all my Rooms
     */
    List<Room> getRooms();

    List<String> getRoomsByName();
}
