package com.openclassrooms.mareuapp.service.ApiServices;


import java.util.List;

public interface ParticipantApiService {

    /**
     * Get List of Participants called by their mail
     */

    List<String> getParticipantsByMail();

}
