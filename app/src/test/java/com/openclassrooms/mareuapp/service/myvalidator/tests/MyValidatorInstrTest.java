package com.openclassrooms.mareuapp.service.myvalidator.tests;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.Participant;
import com.openclassrooms.mareuapp.model.Room;
import com.openclassrooms.mareuapp.model.ValidatorModel;
import com.openclassrooms.mareuapp.service.generators.ParticipantApiServiceGenerator;
import com.openclassrooms.mareuapp.service.generators.RoomApiServiceGenerator;
import com.openclassrooms.mareuapp.utils.MyValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class MyValidatorInstrTest {

    @Test
    public void checkMeetingWithMeetingNull() {
        MyValidator myValidator = new MyValidator();
        ValidatorModel validatorModel = myValidator.checkMeeting(null);
        assertFalse(validatorModel.isValid());
        assertEquals("", validatorModel.getErrorMessage());
    }

    @Test
    public void checkMeetingWithMeetingNameEmpty() {
        MyValidator myValidator = new MyValidator();
        Meeting meeting = new Meeting(555, "", RoomApiServiceGenerator.generateRooms().get(1), new Date(14061991), Arrays.asList(ParticipantApiServiceGenerator.generateParticipants().get(1), ParticipantApiServiceGenerator.generateParticipants().get(2)));
        ValidatorModel validatorModel = myValidator.checkMeeting(meeting);
        assertFalse(validatorModel.isValid());
        assertEquals("Renseignez un nom de réunion avant de valider", validatorModel.getErrorMessage());
    }

    @Test
    public void checkMeetingWithMeetingRoomNull() {
        MyValidator myValidator = new MyValidator();
        Room room = new Room();
        room.setName(null);
        Meeting meeting = new Meeting(555, "Reunion X", room, new Date(14061991), Arrays.asList(ParticipantApiServiceGenerator.generateParticipants().get(1), ParticipantApiServiceGenerator.generateParticipants().get(2)));
        ValidatorModel validatorModel = myValidator.checkMeeting(meeting);
        assertFalse(validatorModel.isValid());
        assertEquals("Renseignez une salle de réunion avant de valider", validatorModel.getErrorMessage());
    }

    @Test
    public void checkMeetingWithMeetingParticipantsListEmpty() {
        MyValidator myValidator = new MyValidator();
        List<Participant> participantList = new ArrayList<>();
        participantList.clear();
        Meeting meeting = new Meeting(555, "Reunion X", new Room(), new Date(14061991), participantList);
        ValidatorModel validatorModel = myValidator.checkMeeting(meeting);
        assertFalse(validatorModel.isValid());
        assertEquals("Selectionnez au moins un participant avant de valider", validatorModel.getErrorMessage());
    }

    @Test
    public void checkMeetingWithMeetingValid() {
        MyValidator myValidator = new MyValidator();
        Meeting meeting = new Meeting(555, "Reunion X", RoomApiServiceGenerator.generateRooms().get(1), new Date(14061991), Arrays.asList(ParticipantApiServiceGenerator.generateParticipants().get(1), ParticipantApiServiceGenerator.generateParticipants().get(2)));
        ValidatorModel validatorModel = myValidator.checkMeeting(meeting);
        assertTrue(validatorModel.isValid());
        assertEquals("", validatorModel.getErrorMessage());
    }
}
