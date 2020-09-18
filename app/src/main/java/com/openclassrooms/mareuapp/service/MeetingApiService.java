package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

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

    /**
     * Get all my Rooms
     */
     List<Room> getRooms();

    /**
     * Add a Room
     */

    void addRoom(Room room);

    /**
     * Delete a Room
     */

    void deleteRoom(Room room);



}
