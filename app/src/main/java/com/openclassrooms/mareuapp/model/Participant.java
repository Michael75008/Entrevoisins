package com.openclassrooms.mareuapp.model;

import java.io.Serializable;

/**
 * Model object representing a participant
 */
public class Participant implements Serializable {

    private int Id;

    private String Mail;

    public Participant(int id, String email) {
        Id = id;
        this.Mail = email;
    }

    public Participant(String email){
        this.Mail = email;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String email) {
        Mail = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
