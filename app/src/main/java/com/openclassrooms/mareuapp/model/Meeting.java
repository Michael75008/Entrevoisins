
package com.openclassrooms.mareuapp.model;


import com.openclassrooms.mareuapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Model object representing a Meeting
 */

public class Meeting implements Serializable {

    /**
     * Identifier
     */

    private int id;

    /**
     * Meeting name
     */

    private String name;

    /**
     * Meeting Room
     */

    private Room mRoom;

    /**
     * Meeting Date
     */

    private Date mDate;

    /**
     * List of Participants
     */

    private List<Participant> mParticipants;

    /**
     * Constructor
     */

    public Meeting(int id, String name, Room room, Date date, List<Participant> participants) {
        this.id = id;
        this.name = name;
        this.mDate = date;
        this.mRoom= room;
        mParticipants = participants;
    }

    public Meeting() {
        this.id = 99;
        this.name = "jeff";
        this.mRoom = new Room(1, R.drawable.ic_circle_pink, "Peach");
        this.mDate = Calendar.getInstance().getTime();
        Participant participant = new Participant(1, "maxime@lamzone.com");
        List<Participant> mParticipant = new ArrayList<>();
        mParticipant.add(participant);
        this.mParticipants = mParticipant;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public List<Participant> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(List<Participant> participants) {
        mParticipants = participants;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        mRoom = room;
    }



}
