package com.openclassrooms.mareuapp.DI;

import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyParticipantApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyRoomApiService;


public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();


    /**
     * Get an instance on MeetingApi
     */

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on MeetingApi
     */

    public static RoomApiService getRoomApiService() {
        return new DummyRoomApiService();
    }

    public static ParticipantApiService getParticipantsApiService() {
        return new DummyParticipantApiService();
    }
}
