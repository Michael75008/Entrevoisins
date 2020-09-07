package com.openclassrooms.mareuapp.model;

import java.io.Serializable;

/**
 * Model object representing a participant
 */
public class Participant implements Serializable {

    private String mail;

    public Participant(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
