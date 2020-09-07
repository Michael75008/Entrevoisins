package com.openclassrooms.mareuapp.model;


import java.io.Serializable;

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
     * Meeting place
     */

    private String place;

    /**
     * Meeting date
     */

    private String meetingDate;

    /**
     * Meeting subject
     */

    private String aboutMeet;

    /**
     * Meeting Participant's Mail List
     */

    private String mail;

    /**
     * Constructor
     */

    public Meeting(int id, String name, String place, String meetingDate, String aboutMeet, String mail) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.meetingDate = meetingDate;
        this.aboutMeet = aboutMeet;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getAboutMeet() {
        return aboutMeet;
    }

    public void setAboutMeet(String aboutMeet) {
        this.aboutMeet = aboutMeet;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
