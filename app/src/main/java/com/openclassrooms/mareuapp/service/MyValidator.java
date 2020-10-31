package com.openclassrooms.mareuapp.service;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.ValidatorModel;

import java.util.Date;


public class MyValidator {

    Date mDate = new Date();

    public ValidatorModel checkMeeting(Meeting meeting) {
        return checkName(meeting);
    }


    public ValidatorModel checkName(Meeting meeting) {
        if (meeting.getName().isEmpty()) {
            return new ValidatorModel(true, "Renseignez un nom de réunion avant de valider");
        }
        return checkRoom(meeting);
    }

    public ValidatorModel checkRoom(Meeting meeting) {
        if (meeting.getRoom() == null) {
            return new ValidatorModel(true, "Renseignez une salle de réunion avant de valider");
        } else {
            return checkParticipants(meeting);
        }
    }

    public ValidatorModel checkParticipants(Meeting meeting) {
        if (meeting.getParticipants().size() == 0) {
            return new ValidatorModel(true, "Selectionnez au moins un participant avant de valider");
        } else {
            return checkDate(meeting);
        }
    }

    public ValidatorModel checkDate(Meeting meeting) {
        if (meeting.getDate().equals(mDate)) {
            return new ValidatorModel(true, "Selectionnez une date et heure de réunion avant de valider");
        } else {
            return new ValidatorModel(false, "");}
    }
    
}
