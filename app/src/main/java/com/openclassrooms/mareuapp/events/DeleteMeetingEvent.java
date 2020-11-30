package com.openclassrooms.mareuapp.events;

import com.openclassrooms.mareuapp.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    public DeleteMeetingEvent(Meeting meeting){
        this.meeting = meeting;
    }
}
