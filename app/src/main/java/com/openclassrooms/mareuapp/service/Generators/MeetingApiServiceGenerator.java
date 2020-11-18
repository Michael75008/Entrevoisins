package com.openclassrooms.mareuapp.service.Generators;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.util.Arrays.asList;

public abstract class MeetingApiServiceGenerator {

    public static List<Meeting> DUMMY_MEETINGS = asList(


            new Meeting(1, "Réunion A", new Room(1, R.drawable.ic_circle_pink, "Peach"), mDate("16-12-2020 18:00:00"), Arrays.asList(new Participant(1, "alex@lamzone.com"), new Participant(4, "maxime@lamzone.com"))),
            new Meeting(2, "Réunion B", new Room(2, R.drawable.ic_circle_green, "Mario"), mDate("20-02-2021 16:00:00"), Arrays.asList(new Participant(2, "paul@lamzone.com"), new Participant(5, "viviane@lamzone.com"))),
            new Meeting(3, "Réunion C", new Room(3, R.drawable.ic_circle_green, "Luigi"), mDate("01-01-2021 14:00:00"), Arrays.asList(new Participant(3, "amandine@lamzone.com"), new Participant(6, "luc@lamzone.com")))
    );

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
