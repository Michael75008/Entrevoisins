package com.openclassrooms.mareuapp.service.Dummys;

import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;
import com.openclassrooms.mareuapp.service.Generators.ParticipantApiServiceGenerator;

import java.util.List;

public class DummyParticipantApiService implements ParticipantApiService {

    private List<Participant> mParticipants = ParticipantApiServiceGenerator.generateParticipants();

    /**
     * Get Participant's List
     */

    public List<Participant> getParticipants() { return mParticipants; }
}
