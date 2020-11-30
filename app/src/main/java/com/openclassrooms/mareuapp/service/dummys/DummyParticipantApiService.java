package com.openclassrooms.mareuapp.service.dummys;

import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.apiservice.ParticipantApiService;
import com.openclassrooms.mareuapp.service.generators.ParticipantApiServiceGenerator;

import java.util.List;

public class DummyParticipantApiService implements ParticipantApiService {

    private List<Participant> mParticipants = ParticipantApiServiceGenerator.generateParticipants();

    /**
     * Get Participant's List
     */

    public List<Participant> getParticipants() { return mParticipants; }
}
