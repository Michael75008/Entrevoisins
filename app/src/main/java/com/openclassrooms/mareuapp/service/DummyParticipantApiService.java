package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

public class DummyParticipantApiService {

    private List<Participant> mParticipants = ParticipantApiServiceGenerator.generateParticipants();

    /**
     * Get Participant's List
     */

    public List<Participant> getParticipants() {return mParticipants;}

    /**
     * Creates a Participant and add it to List
     */

    public void createParticipant(Participant participant) {mParticipants.add(participant);}
}
