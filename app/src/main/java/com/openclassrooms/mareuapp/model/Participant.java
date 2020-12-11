package com.openclassrooms.mareuapp.model;

import java.io.Serializable;

/**
 * Model object representing a participant
 */

public class Participant implements Serializable {

    /**
     * Identifier
     */

    private int id;

    /**
     * Email
     */

    private String mEmail;

    /**
     * Constructor
     */

    public Participant(int id, String email) {
        this.id = id;
        this.mEmail = email;
    }

    /**
     * Getters and setters
     */

    public String getMail() { return mEmail; }
}
