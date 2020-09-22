package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantApiServiceGenerator {
    public static List<Participant> PARTICIPANT = Arrays.asList(
            new Participant(1, "maxime@lamzone.com"),
            new Participant(2, "paul@lamzone.com"),
            new Participant(3, "amandine@lamzone.com"),
            new Participant(4, "viviane@lamzone.com"),
            new Participant(5, "luc@lamzone.com"),
            new Participant(6, "alex@lamzone.com")
    );

    public static List<Participant> generateParticipants() {
        return new ArrayList<>(PARTICIPANT);
    }


}
