package com.openclassrooms.mareuapp.DI;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.DummyParticipantApiService;
import com.openclassrooms.mareuapp.service.DummyRoomApiService;
import com.openclassrooms.mareuapp.service.MeetingApiService;
import com.openclassrooms.mareuapp.service.ParticipantApiService;
import com.openclassrooms.mareuapp.service.RoomApiService;


public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();
    private static RoomApiService service2 = new DummyRoomApiService();
    private static ParticipantApiService service3 = new DummyParticipantApiService();

    /**
     * Get an instance on MeetingApi
     */

    public static MeetingApiService getMeetingApiService(){return service;}

    /**
     * Get always a new instance on MeetingApi
     */

    public static MeetingApiService getNewInstanceApiService(){return new DummyMeetingApiService();}

    public static RoomApiService getRoomApiService(){return new DummyRoomApiService();}

    public static ParticipantApiService getParticipantsApiService(){return new DummyParticipantApiService();}
}
