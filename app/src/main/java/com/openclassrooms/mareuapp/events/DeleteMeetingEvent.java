package com.openclassrooms.mareuapp.events;

import com.openclassrooms.mareuapp.model.Meeting;

public class DeleteMeetingEvent {

    /**
     * Meeting to delete
     */
    public Meeting mMeeting;

    /**
     * Constructor
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}
