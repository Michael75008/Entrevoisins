package com.openclassrooms.mareuapp.model;

import com.google.android.material.chip.Chip;

import java.io.Serializable;

/**
 * Model object representing a participant
 */
public class Participant implements Serializable {

    /**
     * Identifier
     */

    private int Id;

    /**
     * Email
     */

    private String Mail;

    /**
     * Constructor
     * @param id
     * @param email
     */

    public Participant(int id, String email) {
        this.Id = id;
        this.Mail = email; }

    /**
     * Getters and setters
     * @return
     */

    public String getMail() { return Mail; }

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }
}
