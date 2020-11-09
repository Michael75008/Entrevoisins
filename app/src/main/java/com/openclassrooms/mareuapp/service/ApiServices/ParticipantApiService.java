package com.openclassrooms.mareuapp.service.ApiServices;


import com.openclassrooms.mareuapp.model.Participant;

import java.util.List;

public interface ParticipantApiService {

    /**
     * Get all participants
     */

    List<Participant> getParticipants();

}
