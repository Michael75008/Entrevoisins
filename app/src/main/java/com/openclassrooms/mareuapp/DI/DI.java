package com.openclassrooms.mareuapp.DI;
import com.openclassrooms.mareuapp.service.DummyMeetingApiService;
import com.openclassrooms.mareuapp.service.MeetingApiService;


public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    /**
     * Get an instance on MeetingApi
     */

    public static MeetingApiService getMeetingApiService(){return service;}

    /**
     * Get always a new instance on MeetingApi
     */

    public static MeetingApiService getNewInstanceApiService(){return new DummyMeetingApiService();}
}
