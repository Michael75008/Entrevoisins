package com.openclassrooms.mareuapp.DI;
import com.openclassrooms.mareuapp.service.Dummys.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyParticipantApiService;
import com.openclassrooms.mareuapp.service.Dummys.DummyRoomApiService;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;


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
