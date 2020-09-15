package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.openclassrooms.mareuapp.R.id.green_color;
import static com.openclassrooms.mareuapp.R.id.pink_color;

public abstract class DummyMeetingApiServiceGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(

            new Meeting(1, "Réunion A", "describe"),
            new Meeting(2, "Réunion B", "describe"),
            new Meeting(3, "Réunion C", "describe")
    );

    public static List<Meeting> generateMeetings() { return new ArrayList<>(DUMMY_MEETINGS);}

    public static List<Room> ROOM = Arrays.asList(
            new Room(1, pink_color, "Peach"),
            new Room(2, green_color, "Mario"),
            new Room(3, green_color, "Luigi")
    );

    public static List<Room> generateRooms() { return new ArrayList<>(ROOM);}

    public static List<Participant> PARTICIPANT = Arrays.asList(
            new Participant(1, "maxime@lamzone.com"),
            new Participant(2, "paul@lamzone.com"),
            new Participant(3, "amandine@lamzone.com"),
            new Participant(4, "viviane@lamzone.com"),
            new Participant(5, "luc@lamzone.com")
    );

    public static List<Participant> generateParticipants() { return new ArrayList<>(PARTICIPANT);}
}
