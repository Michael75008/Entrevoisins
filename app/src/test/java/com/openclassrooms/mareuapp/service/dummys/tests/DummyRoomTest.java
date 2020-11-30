package com.openclassrooms.mareuapp.service.dummys.tests;

import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.apiservice.RoomApiService;
import com.openclassrooms.mareuapp.service.generators.RoomApiServiceGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DummyRoomTest {

    private RoomApiService roomService;

    @Before
    public void setup() {
        roomService = DI.getNewInstanceRoomApiService();
    }

    @Test
    public void getRoomsWithSuccess() {
        List<Room> rooms = roomService.getRooms();
        List<Room> expectedRooms = RoomApiServiceGenerator.ROOM;
        assertEquals(rooms, expectedRooms);
    }




}
