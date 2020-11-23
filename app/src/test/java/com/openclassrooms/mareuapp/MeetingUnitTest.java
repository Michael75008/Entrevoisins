package com.openclassrooms.mareuapp;

import com.openclassrooms.mareuapp.DI.DI;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.ApiServices.RoomApiService;
import com.openclassrooms.mareuapp.service.Generators.MeetingApiServiceGenerator;
import com.openclassrooms.mareuapp.service.Generators.ParticipantApiServiceGenerator;
import com.openclassrooms.mareuapp.service.Generators.RoomApiServiceGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MeetingUnitTest {

    private MeetingApiService meetingService;
    private RoomApiService roomService;
    private ParticipantApiService participantService;

    @Before
    public void setup() {
        meetingService = DI.getNewInstanceMeetingApiService();
        roomService = DI.getNewInstanceRoomApiService();
        participantService = DI.getNewInstanceParticipantApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = meetingService.getMeetings();
        List<Meeting> expectedMeetings = MeetingApiServiceGenerator.DUMMY_MEETINGS;
        assertEquals(meetings, expectedMeetings);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = meetingService.getMeetings().get(0);
        meetingService.deleteMeeting(meetingToDelete);
        assertFalse(meetingService.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meeting = new Meeting(1);
        meetingService.createMeeting(meeting);
        assertTrue(meetingService.getMeetings().contains(meeting));
    }

    @Test
    public void getMeetingsMatchRoom() {
        Meeting presentMeeting = meetingService.getMeetings().get(1);
        Meeting absentMeeting = meetingService.getMeetings().get(2);
        List<Meeting> expectedMeetings = meetingService.getMeetingsMatchRoom(roomService.getRooms().get(1));
        assertTrue(expectedMeetings.contains(presentMeeting));
        assertFalse(expectedMeetings.contains(absentMeeting));
    }

    @Test
    public void getMeetingsMatchDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.YEAR, 2020);
        Date d = cal.getTime();

        Meeting presentMeeting = meetingService.getMeetings().get(0);
        Meeting absentMeeting = meetingService.getMeetings().get(1);
        List<Meeting> expectedMeetings = meetingService.getMeetingsMatchDate(d);
        assertTrue(expectedMeetings.contains(presentMeeting));
        assertFalse(expectedMeetings.contains(absentMeeting));
    }

    @Test
    public void getRoomsWithSuccess() {
        List<Room> rooms = roomService.getRooms();
        List<Room> expectedRooms = RoomApiServiceGenerator.ROOM;
        assertEquals(rooms, expectedRooms);
    }

    @Test
    public void getParticipantsWithSuccess() {
        List<Participant> participants = participantService.getParticipants();
        List<Participant> expectedParticipants = ParticipantApiServiceGenerator.PARTICIPANT;
        assertEquals(participants, expectedParticipants);
    }
}