package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Meeting;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingApiServiceGenerator.generateMeetings();

    /**
     *  Get Meeting's List
     */

    public List<Meeting> getMeetings() {return meetings;}

    /**
     * Deletes a Meeting from List
     * @param meeting
     */

    public void deleteMeeting(Meeting meeting) { meetings.remove(meeting); }

    /**
     *  Creates a Meeting and add it to List
     * @param meeting
     */

    public void createMeeting(Meeting meeting) {meetings.add(meeting);}

}
