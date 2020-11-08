package com.openclassrooms.mareuapp.service.Generators;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class MeetingApiServiceGenerator {

    public static List<Meeting> DUMMY_MEETINGS = asList(


            new Meeting(1, "Réunion A", new Room(1, R.drawable.ic_circle_pink, "Peach"), new GregorianCalendar(20 + 2000, 11, 18, 14, 00), Arrays.asList(new Participant(1, "alex@lamzone.com"), new Participant(4, "maxime@lamzone.com"))),
            new Meeting(2, "Réunion B", new Room(2, R.drawable.ic_circle_green, "Mario"), new GregorianCalendar(20 + 2000, 12, 18, 14, 00), Arrays.asList(new Participant(2, "paul@lamzone.com"), new Participant(5, "viviane@lamzone.com"))),
            new Meeting(3, "Réunion C", new Room(3, R.drawable.ic_circle_green, "Luigi"), new GregorianCalendar(21 + 2000, 1, 18, 14, 00), Arrays.asList(new Participant(3, "amandine@lamzone.com"), new Participant(6, "luc@lamzone.com")))
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
