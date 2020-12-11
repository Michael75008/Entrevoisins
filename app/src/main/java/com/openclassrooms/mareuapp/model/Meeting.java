package com.openclassrooms.mareuapp.model;


import java.io.Serializable;
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

    private String mName;

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
     * Constructors
     */

    public Meeting(int id, String name, Room room, Date date, List<Participant> participants) {
        this.id = id;
        this.mName = name;
        this.mDate = date;
        this.mRoom = room;
        mParticipants = participants;
    }

    public Meeting(int id) {
        this.id = id;
    }

    public Meeting() {}

    /**
     * Getters and setters
     */

    public String getName() {
        return mName;
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

    public Room getRoom() {
        return mRoom;
    }
}
