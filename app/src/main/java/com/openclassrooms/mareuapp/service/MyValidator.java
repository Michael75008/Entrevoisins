package com.openclassrooms.mareuapp.service;

import android.widget.Toast;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.AddMeetingActivity;

import static android.widget.Toast.LENGTH_SHORT;


public class MyValidator {

    Meeting mMeeting = new Meeting();

    public void checkResult(){
    }
    public void checkName(){}

    public boolean checkRoom(){
        if(mMeeting.getRoom() != null) {
            Toast.makeText(this, "coucou", LENGTH_SHORT).show();
            return true;
        }
    }

    public void checkParticipants(){}
    public void checkDate(){}
    public void checkHour(){}
}
