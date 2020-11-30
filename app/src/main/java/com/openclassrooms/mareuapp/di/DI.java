package com.openclassrooms.mareuapp.di;

import com.openclassrooms.mareuapp.service.apiservice.MeetingApiService;
import com.openclassrooms.mareuapp.service.apiservice.ParticipantApiService;
import com.openclassrooms.mareuapp.service.apiservice.RoomApiService;
import com.openclassrooms.mareuapp.service.dummys.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.dummys.DummyParticipantApiService;
import com.openclassrooms.mareuapp.service.dummys.DummyRoomApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final MeetingApiService meetingService = new DummyMeetingApiService();
    private static final RoomApiService roomService = new DummyRoomApiService();
    private static final ParticipantApiService participantService = new DummyParticipantApiService();

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
