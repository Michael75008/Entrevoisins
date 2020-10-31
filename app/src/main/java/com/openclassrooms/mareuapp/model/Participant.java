package com.openclassrooms.mareuapp.model;

import com.google.android.material.chip.Chip;

import java.io.Serializable;

/**
 * Model object representing a participant
 */
public class Participant implements Serializable {

    private int Id;

    private String Mail;

    private Chip mChip;

    public Participant(int id, String email){
        this.Id = id;
        this.Mail = email;
    }

    public Participant(Chip childAt) {
        this.mChip = childAt;
    }

    public Participant(String participant) {
        this.Mail = participant;
    }

    public Participant() {

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
