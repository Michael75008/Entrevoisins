package com.openclassrooms.mareuapp.Utils;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.ValidatorModel;


public class MyValidator {

    ValidatorModel validatorModel;

    public MyValidator() {
        this.validatorModel = new ValidatorModel(true, "");
    }

    public ValidatorModel checkMeeting(Meeting meeting) {
        checkRoom(meeting);
        checkName(meeting);
        checkParticipants(meeting);
        checkMeet(meeting);
        return validatorModel;
    }

    public void checkName(Meeting meeting) {
        if (meeting.getName().isEmpty()) {
            validatorModel.setValid(false);
            validatorModel.setErrorMessage("Renseignez un nom de réunion avant de valider");
        }
    }

    public void checkRoom(Meeting meeting) {
        if (meeting.getRoom() == null) {
            validatorModel.setValid(false);
            validatorModel.setErrorMessage("Renseignez une salle de réunion avant de valider");
        }
    }

    public void checkParticipants(Meeting meeting) {
        if (meeting.getParticipants().size() == 0) {
            validatorModel.setValid(false);
            validatorModel.setErrorMessage("Selectionnez au moins un participant avant de valider");
        }
    }

    public void checkMeet(Meeting meeting) {
        if (!meeting.getName().isEmpty() && meeting.getRoom() != null && meeting.getParticipants().size() != 0) {
            validatorModel.setValid(true);
            validatorModel.setErrorMessage("");

        }
    }
}
