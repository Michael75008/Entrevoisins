package com.openclassrooms.mareuapp.service.Generators;

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

    public static List<Meeting> DUMMY_MEETINGS = asList(


            new Meeting(1, "Réunion A", new Room(1, R.drawable.ic_circle_pink, "Peach"), new Date(2020, 10, 6, 14, 00), Arrays.asList(new Participant("alex@lamzone.com"), new Participant("maxime@lamzone.com"))),
            new Meeting(2, "Réunion B", new Room(2, R.drawable.ic_circle_green, "Mario"), new Date(2020, 10, 7, 16, 00), Arrays.asList(new Participant("paul@lamzone.com"), new Participant("viviane@lamzone.com"))),
            new Meeting(3, "Réunion C", new Room(3, R.drawable.ic_circle_green, "Luigi"), new Date(2020, 10, 8, 18, 00), Arrays.asList(new Participant("amandine@lamzone.com"), new Participant("luc@lamzone.com")))
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
