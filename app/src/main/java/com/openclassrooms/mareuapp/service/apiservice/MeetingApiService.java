package com.openclassrooms.mareuapp.service.apiservice;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Room;

import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    /**
     * Gets all Meetings
     */

    List<Meeting> getMeetings();

    /**
     * Deletes a Meeting
     */

    void deleteMeeting(Meeting meeting);

    /**
     * Creates a Meeting
     */

    void createMeeting(Meeting meeting);

    /**
     * Get Meeting's list filtered by Room
     */

    List<Meeting> getMeetingsMatchRoom(Room room);

    /**
     * Get Meeting's list filtered by date
     */

    List<Meeting> getMeetingsMatchDate(Date date);

    /**
     * Check if a meeting already exist. Will compare the date and the room in order to decide
     * @param meeting the current meeting we want to check if a similar already exist
     * @return true if a meeting is already created with the same room and date. Else return no
     */

    boolean isMeetingAlreadyCreated(Meeting meeting);

}
