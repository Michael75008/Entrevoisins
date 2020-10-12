package com.openclassrooms.mareuapp.service.ApiServices;

import com.openclassrooms.mareuapp.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Gets all my Meetings
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


}
