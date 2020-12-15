package com.openclassrooms.mareuapp.service.Dummys.tests;

import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.apiservice.MeetingApiService;
import com.openclassrooms.mareuapp.service.apiservice.RoomApiService;
import com.openclassrooms.mareuapp.service.generators.MeetingApiServiceGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DummyMeetingUnitTest {

    private MeetingApiService meetingService;
    private RoomApiService roomService;
    private Meeting fakeMeeting;
    private Date fakeDate;
    private Room fakeRoom;


    @Before
    public void setup() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.YEAR, 2020);
        fakeDate = cal.getTime();
        fakeMeeting = new Meeting();
        fakeMeeting.setDate(fakeDate);
        fakeRoom = new Room(30,30,"fakeRoom");
        meetingService = DI.getNewInstanceMeetingApiService();
        roomService = DI.getRoomApiService();
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
        Meeting presentMeeting = meetingService.getMeetings().get(0);
        Meeting absentMeeting = meetingService.getMeetings().get(1);
        List<Meeting> expectedMeetings = meetingService.getMeetingsMatchRoom(roomService.getRooms().get(1));
        assertFalse(expectedMeetings.contains(presentMeeting));
        assertTrue(expectedMeetings.contains(absentMeeting));
        assertNotNull(expectedMeetings);
    }

    @Test
    public void getMeetingsMatchDate() {
        meetingService.createMeeting(fakeMeeting);
        List<Meeting> expectedMeetings = meetingService.getMeetingsMatchDate(fakeDate);
        assertTrue(expectedMeetings.contains(fakeMeeting));
        assertNotNull(expectedMeetings);
    }

    @Test
    public void checkMeetingAlreadyCreated() {
        fakeMeeting.setDate(fakeDate);
        fakeMeeting.setRoom(fakeRoom);
        Meeting meeting = new Meeting(1);
        meeting.setDate(fakeDate);
        meeting.setRoom(fakeRoom);
        meetingService.createMeeting(meeting);
        boolean result = meetingService.isMeetingAlreadyCreated(fakeMeeting);
        assertTrue(result);
    }

    @Test
    public void checkMeetingNotAlreadyCreated() {
        fakeMeeting.setDate(fakeDate);
        fakeMeeting.setRoom(fakeRoom);
        boolean result = meetingService.isMeetingAlreadyCreated(fakeMeeting);
        assertFalse(result);
    }

    @Test
    public void checkMeetingNotAlreadyCreatedDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.YEAR, 2020);
        Date date = cal.getTime();
        fakeMeeting.setDate(fakeDate);
        fakeMeeting.setRoom(fakeRoom);
        Meeting meeting = new Meeting(1);
        meeting.setDate(date);
        meeting.setRoom(fakeRoom);
        meetingService.createMeeting(meeting);
        boolean result = meetingService.isMeetingAlreadyCreated(fakeMeeting);
        assertFalse(result);
    }

    @Test
    public void checkMeetingNotAlreadyCreatedRoom() {
        Room room = new Room(32,32,"room");
        fakeMeeting.setDate(fakeDate);
        fakeMeeting.setRoom(fakeRoom);
        Meeting meeting = new Meeting(1);
        meeting.setDate(fakeDate);
        meeting.setRoom(room);
        meetingService.createMeeting(meeting);
        boolean result = meetingService.isMeetingAlreadyCreated(fakeMeeting);
        assertFalse(result);
    }
}