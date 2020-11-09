
package com.openclassrooms.mareuapp.model;


import java.io.Serializable;
import java.util.GregorianCalendar;
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

    private GregorianCalendar mDate;

    /**
     * List of Participants
     */

    private List<Participant> mParticipants;

    /**
     * Constructors
     */

    public Meeting(int id, String name, Room room, GregorianCalendar date, List<Participant> participants) {
        this.id = id;
        this.name = name;
        this.mDate = date;
        this.mRoom = room;
        mParticipants = participants;
    }

    public Meeting() { }

    /**
     * Getters and setters
     * @return
     */

    public String getName() { return name; }

    public GregorianCalendar getDate() { return mDate; }

    public List<Participant> getParticipants() { return mParticipants; }

    public Room getRoom() { return mRoom; }

    public void setRoom(Room room) { mRoom = room; }
}
