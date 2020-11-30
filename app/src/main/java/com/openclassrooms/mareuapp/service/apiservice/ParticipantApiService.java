package com.openclassrooms.mareuapp.service.apiservice;


import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

public interface ParticipantApiService {

    /**
     * Get all participants
     */

    List<Participant> getParticipants();

}
