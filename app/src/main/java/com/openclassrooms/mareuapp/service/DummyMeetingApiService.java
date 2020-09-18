package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingApiServiceGenerator.generateMeetings();
    private List<Participant> mParticipants = DummyMeetingApiServiceGenerator.generateParticipants();
    private final List<Room> mRooms = DummyMeetingApiServiceGenerator.generateRooms();

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

    /**
     * Get Participant's List
     */

    public List<Participant> getParticipants() {return mParticipants;}

    /**
     * Deletes a Participant from List
     */

    public void deleteParticipant(Participant participant) {mParticipants.remove(participant);}

    /**
     * Creates a Participant and add it to List
     */

    public void createParticipant(Participant participant) {mParticipants.add(participant);}

    /**
     * Get Room's List
     */

    public List<Room> getRooms() {return mRooms;}

    public void addRoom(Room room) {mRooms.add(room);}

    @Override
    public void deleteRoom(Room room) {mRooms.remove(room); }

}
