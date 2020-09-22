package com.openclassrooms.mareuapp.service;


import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

public interface ParticipantApiService {


    /**
     * Get List of Participants
     */
    List<Participant> getParticipants();

    /**
     * Creates a Participant
     */

    void createParticipant(Participant participant);
}
