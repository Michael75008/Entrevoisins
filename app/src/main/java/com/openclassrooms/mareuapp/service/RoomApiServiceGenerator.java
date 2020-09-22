package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomApiServiceGenerator {

    public static List<Room> ROOM = Arrays.asList(
            new Room(1, R.drawable.ic_circle_pink, "Peach"),
            new Room(2, R.drawable.ic_circle_green, "Mario"),
            new Room(3, R.drawable.ic_circle_green, "Luigi")
    );

    public static List<Room> generateRooms() {
        return new ArrayList<>(ROOM);
    }
}
