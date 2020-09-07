package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DummyMeetingApiServiceGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(

            new Meeting(1, "Réunion A", "Peach", "14:00" ,  "La Réunion A aura lieu dans la Salle Peach, à 14h00", "maxime@lamzone.com, alex@lamzone.com"),
            new Meeting(2, "Réunion B", "Mario", "16:00", "La Réunion B aura lieu dans la Salle Mario, à 14h00", "paul@lamzone.com, viviane@lamzone.com"),
            new Meeting(3, "Réunion C", "Luigi", "19:00", "La Réunion C aura lieu dans la Salle Luigi, à 19h00", "amandine@lamzone.com, viviane@lamzone.com")
    );




    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
