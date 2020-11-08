package com.openclassrooms.mareuapp.service.Dummys;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.Generators.MeetingApiServiceGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    /**
     * Get Meeting's list filtered by Room
     *
     * @param roomName
     * @return
     */

    public List<Meeting> getMeetingsMatchRoomName(String roomName) {
        List<Meeting> currentMeetings = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            Meeting meeting = meetings.get(i);
            if (meeting.getRoom().getName().equals(roomName.trim()))
                currentMeetings.add(meeting);
        }
        return currentMeetings;
    }

    /**
     * Get Meeting's list filtered by date
     *
     * @param date
     * @return
     */


    public List<Meeting> getMeetingMatchDate(GregorianCalendar date) {
        List<Meeting> currentMeetings = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            Meeting meeting = meetings.get(i);
            if (meeting.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH) && meeting.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) && meeting.getDate().get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH))
                currentMeetings.add(meeting);
        }
        return currentMeetings;
    }
}