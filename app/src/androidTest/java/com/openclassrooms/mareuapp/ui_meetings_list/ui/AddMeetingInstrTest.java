package com.openclassrooms.mareuapp.ui_meetings_list.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareuapp.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class AddMeetingInstrTest {

    int PARTICIPANTS_COUNT = DI.getParticipantService().getParticipants().size();
    int ROOMS_COUNT = DI.getRoomApiService().getRooms().size();
    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityRule =
            new ActivityScenarioRule<AddMeetingActivity>(AddMeetingActivity.class);

    @Test
    public void addMeeting_checkBackToPreviousActivity() {
        //Click on back arrow to return to previous activity
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
        //Ensure we change activity checking appbar title
        onView(Matchers.allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("Ma Réu")));
    }

    @Test
    public void addMeeting_checkMeetingNameInput() {
        //Ensure we find an hint text indicating where we put the meeting name
        onView(withHint("Topic de la réunion")).check(matches(isDisplayed()));
        //Click on it and check we can write the meeting name
        onView(withHint("Topic de la réunion")).perform(click()).perform(replaceText("Réunion X"));
        //Check that the meeting name was changed
        onView(withId(R.id.meeting_topic))
                .check(matches(withText("Réunion X")));
    }

    @Test
    public void addMeeting_checkParticipantsSelection() {
        //Ensure we find all participants on screen for choice
        onView(allOf(isDisplayed(), withId(R.id.participant_recycler_view))).check(withItemCount(PARTICIPANTS_COUNT));
        //Check we can select Viviane and Paul
        onView(allOf(withId(R.id.email), withText("viviane@lamzone.com"))).perform(click());
        onView(allOf(withId(R.id.email), withText("paul@lamzone.com"))).perform(click());
        //Ensure we can unselect Viviane
        onView(allOf(withId(R.id.email), withText("viviane@lamzone.com"))).perform(scrollTo(), click()).check(matches(not(isChecked())));
    }

    @Test
    public void addMeeting_CheckRoomSelection() {
        //Ensure we find all rooms on screen for choice
        onView(allOf(isDisplayed(), withId(R.id.room_list))).check(withItemCount(ROOMS_COUNT));
        //Check we can select a room
        onView(allOf(withId(R.id.room), withText("Luigi"))).perform(scrollTo(), click());
        //Check we can change the room before validation
        onView(allOf(withId(R.id.room), withText("Mario"))).perform(scrollTo(), click());
        //Ensure we can only select one room per meeting
        onView(allOf(withId(R.id.room), withText("Luigi"))).check(matches(not(isChecked())));
    }


    @Test
    public void addMeeting_checkDateAndTimeInput() {
        //Ensure that we find current date and current time as default choice
        onView(allOf(withId(R.id.date_image))).perform(click());
        //Check we can choose date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 12, 24));
        //Perform a click to confirm date
        onView(withText("OK")).perform(scrollTo()).perform(click());
        // Check we can choose time
        //Ensure that we changed it on screen
    }

}