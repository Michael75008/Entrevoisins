package com.openclassrooms.mareuapp;


import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareuapp.ui_meetings_list.ui.AddMeetingActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class AddMeetingInstrTest {

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityRule =
            new ActivityTestRule<>(AddMeetingActivity.class);

    @Before
    public void setUp() {
        AddMeetingActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    /**
     * UI elements testing
     */

    @Test
    public void addMeeting_checkBackToPreviousActivity() {
        onView(Matchers.allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("Ma Réu")));
    }

    @Test
    public void addMeeting_checkRoomsSelection() {
    }

    @Test
    public void addMeeting_checkParticipantsSelection() {
    }

    @Test
    public void addMeeting_checkNameInput() {
    }

    @Test
    public void addMeeting_checkDefaultDate() {
    }

    @Test
    public void addMeeting_checkDateSelection() {
    }

    @Test
    public void addMeeting_checkDefaultTime() {
    }

    @Test
    public void addMeeting_checkTimeSelection() {
    }


    /**
     * Validator testing
     */

    @Test
    public void validator_checkRoomError() {
    }

    @Test
    public void validator_checkParticipantsError() {
    }

    @Test
    public void validator_checkNameError() {
    }

    @Test
    public void validator_lambda_checkMeetingValidation() {
    }

    @Test
    public void validator_lambda_checkMeetingError() {
    }
}