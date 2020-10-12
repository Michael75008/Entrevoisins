package com.openclassrooms.mareuapp.service.Dummys;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.Generators.MeetingApiServiceGenerator;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;

import java.util.List;


public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = MeetingApiServiceGenerator.generateMeetings();


    /**
     * Get Meeting's List
     */

    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Deletes a Meeting from List
     *
     * @param meeting
     */

    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Creates a Meeting and add it to List
     *
     * @param meeting
     */

    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

}
