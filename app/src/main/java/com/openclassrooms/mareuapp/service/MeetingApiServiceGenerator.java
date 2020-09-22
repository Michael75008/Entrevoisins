package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class MeetingApiServiceGenerator {

    ArrayList<Participant> mParticipants = new ArrayList<>();

    public static List<Meeting> DUMMY_MEETINGS = asList(


            new Meeting(1, "Réunion A", "describe", new Room(1, R.drawable.ic_circle_pink, "Peach"), new Date(2020, 10, 6, 14, 00), Arrays.asList(new Participant(6, "alex@lamzone.com"), new Participant(1, "maxime@lamzone.com"))),
            new Meeting(2, "Réunion B", "describe", new Room(2, R.drawable.ic_circle_green, "Mario"), new Date(2020, 10, 7, 16, 00), Arrays.asList(new Participant(2, "paul@lamzone.com"), new Participant(4, "viviane@lamzone.com"))),
            new Meeting(3, "Réunion C", "describe", new Room(3, R.drawable.ic_circle_green, "Luigi"), new Date(2020, 10, 8, 18, 00), Arrays.asList(new Participant(3, "amandine@lamzone.com"), new Participant(5, "luc@lamzone.com")))
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
