package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Get all my Meetings
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