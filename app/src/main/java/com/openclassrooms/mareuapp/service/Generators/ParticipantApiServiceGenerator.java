package com.openclassrooms.mareuapp.service.Generators;

import com.openclassrooms.mareuapp.model.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantApiServiceGenerator {
    public static List<Participant> PARTICIPANT = Arrays.asList(
            new Participant("maxime@lamzone.com"),
            new Participant("paul@lamzone.com"),
            new Participant("amandine@lamzone.com"),
            new Participant("viviane@lamzone.com"),
            new Participant("luc@lamzone.com"),
            new Participant("alex@lamzone.com")
    );

    public static List<Participant> generateParticipants() {
        return new ArrayList<>(PARTICIPANT);
    }


}
