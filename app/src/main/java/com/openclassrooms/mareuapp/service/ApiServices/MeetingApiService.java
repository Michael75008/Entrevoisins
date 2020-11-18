package com.openclassrooms.mareuapp.service.ApiServices;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Room;

import java.util.Date;
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
     */

    List<Meeting> getMeetingsMatchRoom(Room room);

    /**
     * Get Meeting's list filtered by date
     *
     * @param date
     * @return
     */

    List<Meeting> getMeetingsMatchDate(Date date);

}
