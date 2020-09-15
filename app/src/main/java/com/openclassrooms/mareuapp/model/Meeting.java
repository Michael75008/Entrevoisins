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

    private String name;

    /**
     * Meeting subject
     */

    private String aboutMeet;

    private Room mRoom;


    private Date mDate;

    private List<Participant> mParticipants;

    /**
     * Constructor
     */

    public Meeting(int id, String name, String aboutMeet, Room room, Date date, List<Participant> participants) {
        this.id = id;
        this.name = name;
        this.aboutMeet = aboutMeet;
        mRoom = room;
        mDate = date;
        mParticipants = participants;
    }

    public Meeting(int id, String name, String aboutMeet) {
        this.id = id;
        this.name = name;
        this.aboutMeet = aboutMeet;
    }

    public String getName() {
        return name;
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
}