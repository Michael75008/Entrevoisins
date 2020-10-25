package com.openclassrooms.mareuapp.service;

import android.content.Context;
import android.widget.Toast;

import com.openclassrooms.mareuapp.model.Meeting;


public class MyValidator {

    Context mContext;

    public MyValidator(Context context, Meeting meeting) {
        this.mContext = context;
    }


    public void checkResult() {
    }

    public void checkName() {
    }

    public void checkRoom() {
        Toast.makeText(mContext, "coucou", Toast.LENGTH_LONG).show();
    }


    public void checkParticipants() {
    }

    public void checkDate() {
    }

    public void checkHour() {
    }
}
