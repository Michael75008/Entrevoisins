package com.openclassrooms.mareuapp.service.Dummys;

import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.Generators.ParticipantApiServiceGenerator;
import com.openclassrooms.mareuapp.service.ApiServices.ParticipantApiService;

import java.util.ArrayList;
import java.util.List;

public class DummyParticipantApiService implements ParticipantApiService {

    private List<Participant> mParticipants = ParticipantApiServiceGenerator.generateParticipants();

    /**
     * Get Participant's List
     */

    public List<Participant> getParticipants() {return mParticipants;}

    public List<String> getParticipantsByMail() {
        List<String> currentParticipants = new ArrayList<>();
        for (int i = 0; i < mParticipants.size(); i++) {
            String participant = mParticipants.get(i).getMail();
            if (participant != null) {
                currentParticipants.add(participant);
            }
        }
        return currentParticipants;
    }
}
