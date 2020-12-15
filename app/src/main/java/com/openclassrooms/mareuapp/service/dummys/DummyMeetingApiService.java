package com.openclassrooms.mareuapp.service.dummys;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.service.apiservice.MeetingApiService;
import com.openclassrooms.mareuapp.service.generators.MeetingApiServiceGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> mMeetings = MeetingApiServiceGenerator.generateMeetings();

    /**
     * Get meeting's List
     */

    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    /**
     * Deletes a meeting from List
     */

    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    /**
     * Creates a meeting and add it to List
     */

    public void createMeeting(Meeting meeting) { mMeetings.add(meeting); }

    /**
     * Get meeting's list filtered by date
     */

    public List<Meeting> getMeetingsMatchDate(Date date) {
        List<Meeting> currentMeetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting meeting = mMeetings.get(i);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
            if (sdf.format(meeting.getDate()).equals(sdf.format(date)))
                currentMeetings.add(meeting);
        }
        return currentMeetings;
    }

    /**
     * Get meeting's list filtered by room name
     */

    public List<Meeting> getMeetingsMatchRoom(Room room) {
        List<Meeting> currentMeetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting meeting = mMeetings.get(i);
            if (meeting.getRoom().getName().equals(room.getName()))
                currentMeetings.add(meeting);
        }
        return currentMeetings;
    }

    private boolean isTheSameDate(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:HH:mm", Locale.FRANCE);
        return sdf.format(date1).equals(sdf.format(date2));
    }
    @Override
    public boolean isMeetingAlreadyCreated(Meeting meeting) {
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting currentmeeting = mMeetings.get(i);
            if (currentmeeting.getRoom().getName().equals(meeting.getRoom().getName())
                    && isTheSameDate(currentmeeting.getDate(), meeting.getDate())) {
                return true;
            }
        }
        return false;
    }
}
