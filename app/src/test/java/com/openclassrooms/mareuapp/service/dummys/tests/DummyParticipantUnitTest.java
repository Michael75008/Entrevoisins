package com.openclassrooms.mareuapp.service.dummys.tests;

import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.service.apiservice.ParticipantApiService;
import com.openclassrooms.mareuapp.service.generators.ParticipantApiServiceGenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DummyParticipantUnitTest {

    private ParticipantApiService participantService;

    @Before
    public void setup() {
        participantService = DI.getNewInstanceParticipantApiService();
    }

    @Test
    public void getParticipantsWithSuccess() {
        List<Participant> participants = participantService.getParticipants();
        List<Participant> expectedParticipants = ParticipantApiServiceGenerator.PARTICIPANT;
        assertEquals(participants, expectedParticipants);
    }
}
