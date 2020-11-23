package com.openclassrooms.mareuapp.DI;

import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyParticipantApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyRoomApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MeetingApiService meetingService = new DummyMeetingApiService();
    private static RoomApiService roomService = new DummyRoomApiService();
    private static ParticipantApiService participantService = new DummyParticipantApiService();

    /**
     * Get an instance on MeetingApiService
     */

    public static MeetingApiService getMeetingApiService() {
        return meetingService;
    }

    /**
     * Get an instance on RoomApiService
     */

    public static RoomApiService getRoomApiService() {
        return roomService;
    }

    /**
     * Get an instance on ParticipantApiService
     */

    public static ParticipantApiService getParticipantService() {
        return participantService;
    }

    /**
     * Get always a new instance on MeetingApiService.
     */

    public static MeetingApiService getNewInstanceMeetingApiService() {
        return new DummyMeetingApiService();
    }

    public static RoomApiService getNewInstanceRoomApiService() {
        return new DummyRoomApiService();
    }

    public static ParticipantApiService getNewInstanceParticipantApiService() {
        return new DummyParticipantApiService();
    }
}
