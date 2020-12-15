package com.openclassrooms.mareuapp.ui_meetings_list.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingInstrTest {

    int PARTICIPANTS_COUNT = DI.getParticipantService().getParticipants().size();
    int ROOMS_COUNT = DI.getRoomApiService().getRooms().size();

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityRule =
            new ActivityTestRule<>(AddMeetingActivity.class);



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
    public void addMeeting_shouldShowEveryParticipantAndLetSelectOrUnselectIt() {
        //Ensure we find all participants on screen for choice
        onView(allOf(isDisplayed(), withId(R.id.participant_recycler_view))).check(withItemCount(PARTICIPANTS_COUNT));
        //Check we can select Viviane and Paul
        onView(allOf(withId(R.id.email), withText("viviane@lamzone.com"))).perform(click());
        onView(allOf(withId(R.id.email), withText("paul@lamzone.com"))).perform(click());
        //Ensure we can unselect Viviane
        onView(allOf(withId(R.id.email), withText("viviane@lamzone.com"))).perform(scrollTo(), click()).check(matches(not(isChecked())));
    }

    @Test
    public void addMeeting_shouldShowEveryRoomAndLetItChooseOnlyOne() {
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
    public void addMeeting_shouldDisplayDateOnScreenWhenChoiceIsDone() {
        //Click on date icon to open date picker
        onView(allOf(withId(R.id.date_image))).perform(click());
        //Select a date in future on calendar: 24/12/2050
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2050, 12, 24));
        //Perform a click to confirm date
        onView(withText("OK")).perform(click());
        //Ensure that new date is shown to user
        //Todo : il te faut plus de rigeur ! tu as ajouter un espace c est pour ca que ce test ne marche pas !
        // Si tu lis pk le test ne marche pas tu verras..
        onView(withId(R.id.date_view)).check(matches(withText("Date de réunion choisie :  24/12/2050")));
        //Click on time icon to open time picker
        onView(allOf(withId(R.id.time_image))).perform(click());
        // Select a time: 18:30
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(18, 30));
        //Ensure that new time is shown to user
        onView(withText("OK")).perform(click());
        //Ensure that new hour has been correctly displayed on screen
        //Todo : de meme ici
        onView(withId(R.id.time_view)).check(matches(withText("Heure de réunion choisie :  18:30")));
    }
}