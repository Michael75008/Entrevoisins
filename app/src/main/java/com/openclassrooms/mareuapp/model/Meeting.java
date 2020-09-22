
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

    /**
     * Meeting Room
     */

    private String mRoom;

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

    public Meeting(int id, String name, String aboutMeet, Room room, Date date, List<Participant> participants) {
        this.id = id;
        this.name = name;
        this.aboutMeet = aboutMeet;
        Date mdate = date;
        Room mRoom = room;
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

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }

    public String getAboutMeet() {
        return aboutMeet;
    }

    public void setAboutMeet(String aboutMeet) {
        this.aboutMeet = aboutMeet;
    }


}
