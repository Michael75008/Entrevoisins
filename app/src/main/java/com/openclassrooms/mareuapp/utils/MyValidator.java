package com.openclassrooms.mareuapp.utils;
import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.model.ValidatorModel;
import com.openclassrooms.mareuapp.service.apiservice.MeetingApiService;


public class MyValidator {

    ValidatorModel validatorModel;
    MeetingApiService mApiService;

    public MyValidator() {
        this.validatorModel = new ValidatorModel(true, R.string.void_text);
        this.mApiService = DI.getMeetingApiService();
    }

    public ValidatorModel checkMeeting(Meeting meeting) {
        validatorModel.setValid(true);
        if (meeting == null) {
            validatorModel.setValid(false);
            return validatorModel;
        }
        check(meeting.getName().isEmpty(), R.string.choose_meeting_name_before_validate);
        check(meeting.getRoom().getName() == null, R.string.choose_meeting_room_before_validate);
        check(meeting.getParticipants().size() == 0, R.string.choose_meeting_participant_before_validate);

        return validatorModel;
    }


    private void check(boolean condition, int msgId) {
        if (condition) {
            validatorModel.setValid(false);
            validatorModel.setErrorMessage(msgId);
        }
    }
}
