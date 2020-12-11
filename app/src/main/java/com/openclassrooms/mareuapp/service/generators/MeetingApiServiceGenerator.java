package com.openclassrooms.mareuapp.service.generators;

import com.openclassrooms.mareuapp.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

public abstract class MeetingApiServiceGenerator {

    public static List<Meeting> DUMMY_MEETINGS = asList(

            new Meeting(1, "Réunion A", RoomApiServiceGenerator.generateRooms().get(0), mDate("16-12-2020 18:00:00"), Arrays.asList(ParticipantApiServiceGenerator.generateParticipants().get(0), ParticipantApiServiceGenerator.generateParticipants().get(5))),
            new Meeting(2, "Réunion B", RoomApiServiceGenerator.generateRooms().get(1), mDate("20-02-2021 16:00:00"), Arrays.asList(ParticipantApiServiceGenerator.generateParticipants().get(1), ParticipantApiServiceGenerator.generateParticipants().get(3))),
            new Meeting(3, "Réunion C", RoomApiServiceGenerator.generateRooms().get(1), mDate("01-01-2021 14:00:00"), Arrays.asList(ParticipantApiServiceGenerator.generateParticipants().get(2), ParticipantApiServiceGenerator.generateParticipants().get(4))));

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static Date mDate(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRANCE);
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTime();
    }
}
