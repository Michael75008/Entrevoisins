package com.openclassrooms.mareuapp.utils;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.ValidatorModel;


public class MyValidator {

    ValidatorModel validatorModel;
    private final static String ERROR_NAME = "Renseignez un nom de réunion avant de valider";
    private final static String ERROR_ROOM = "Renseignez une salle de réunion avant de valider";
    private final static String ERROR_PARTICIPANT = "Selectionnez au moins un participant avant de valider";


    public MyValidator() {
        this.validatorModel = new ValidatorModel(true, "");
    }

    public ValidatorModel checkMeeting(Meeting meeting) {
        validatorModel.setValid(true);
        if (meeting == null) {
            validatorModel.setValid(false);
            return validatorModel;
        }
        check(meeting.getName().isEmpty(), ERROR_NAME);
        check(meeting.getRoom().getName() == null, ERROR_ROOM);
        check(meeting.getParticipants().size() == 0, ERROR_PARTICIPANT);
        return validatorModel;
    }

    private void check(boolean condition, String msg) {
        if (condition) {
            validatorModel.setValid(false);
            validatorModel.setErrorMessage(msg);
        }
    }
}
