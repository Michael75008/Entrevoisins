package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

public class DummyParticipantApiService implements ParticipantApiService {

    private List<Participant> mParticipants = ParticipantApiServiceGenerator.generateParticipants();

    /**
     * Get Participant's List
     */

    public List<Participant> getParticipants() {return mParticipants;}

}
