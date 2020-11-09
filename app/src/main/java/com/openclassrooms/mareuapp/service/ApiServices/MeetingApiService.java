package com.openclassrooms.mareuapp.service.ApiServices;

import com.openclassrooms.mareuapp.model.Meeting;

import java.util.GregorianCalendar;
import java.util.List;

public interface MeetingApiService {

    /**
     * Gets all Meetings
     *
     * @return(@link list)
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a Meeting
     *
     * @param meeting
     */

    void deleteMeeting(Meeting meeting);

    /**
     * Creates a Meeting
     *
     * @param meeting
     */

    void createMeeting(Meeting meeting);

    /**
     * Get Meeting's list filtered by Room
     * @param roomName
     * @return
     */

    List<Meeting> getMeetingsMatchRoomName(String roomName);

    /**
     * Get Meeting's list filtered by date
     * @param date
     * @return
     */

    List<Meeting> getMeetingMatchDate(GregorianCalendar date);


}
