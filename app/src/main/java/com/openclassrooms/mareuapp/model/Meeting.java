package com.openclassrooms.mareuapp.model;


import android.graphics.Color;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Model object representing a Meeting
 */

public class Meeting implements Serializable {

    private static Random sRandom = new Random();
    private static Integer sLastId = 3;

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


    private String mRoom;


    private String mDate;

    private String mHour;

    private List<Participant> mParticipants;

    private Integer mColor;

    /**
     * Constructor
     */

    public Meeting(String name, String aboutMeet, String room, String date, String hour) {
        id = ++sLastId;
        this.name = name;
        this.aboutMeet = aboutMeet;
        mRoom = room;
        mDate = date;
        mHour = hour;
        mColor = Color.argb(
                sRandom.nextInt(255),
                sRandom.nextInt(255),
                sRandom.nextInt(255),
                sRandom.nextInt(255));
    }

    public Meeting(int id, String name, String aboutMeet) {
        this.id = id;
        this.name = name;
        this.aboutMeet = aboutMeet;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
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

    public String getHour() {
        return mHour;
    }

    public void setHour(String hour) {
        mHour = hour;
    }

    public Integer getColor(){
        return mColor;
    }
}
