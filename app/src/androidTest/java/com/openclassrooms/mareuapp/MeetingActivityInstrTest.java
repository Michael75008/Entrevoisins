package com.openclassrooms.mareuapp;

import android.widget.TextView;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareuapp.Utils.DeleteViewAction;
import com.openclassrooms.mareuapp.ui_meetings_list.ui.MeetingActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareuapp.R.string.ActionBarAddMeeting;
import static com.openclassrooms.mareuapp.Utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(JUnit4.class)
public class MeetingActivityInstrTest {

    private Calendar mCalendar = Calendar.getInstance();
    private Date mDate = mCalendar.getTime();

    @Rule
    public ActivityTestRule<MeetingActivity> mActivityRule =
            new ActivityTestRule<>(MeetingActivity.class);

    @Before
    public void setUp() {
        MeetingActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    /**
     * Main functions
     */

    @Test
    public void myParticipantsList_shouldNotBeEmpty() {
        //Check if list of meeting is displayed
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_meetings)))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myParticipantsList_shouldRemoveItem() {
        //Check the number of elements: 3
        int ITEMS_COUNT = 3;
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Remove item at position 1 performing click on delete icon
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        //Check the number of elements: 3-1 = 2
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myParticipantsList_shouldDisplayAddMeetingPage() {
        //Perform a click to open add meeting activity
        onView(withId(R.id.fab_add_meeting)).perform(click());
        //Check if we can click in validate meeting button
        onView(Matchers.allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText(ActionBarAddMeeting)));
    }

    /**
     * Filter all meetings
     */

    @Test
    public void myParticipantsList_shouldDisplayAllMeetings() {
        //Perform a click to filter list for Mario Room
        //Perform a click to filter list for full list
        //Check matches with getMeetings list
    }


    /**
     * Filter by date
     */

    @Test
    public void myParticipantsList_shouldDisplayMeetingsForGivenDate() {
        //Perform a click to filter list for given date


    }

    public void myParticipantsList_shouldDisplayMessageWhenEmptyDate() {
        //Perform a click to filter list for a empty given date
        //Check if empty message is displayed at screen
    }


    /**
     * Filter by Room
     */

    @Test
    public void myParticipantList_shouldDisplayMeetingsForGivenRoom() {
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
    }

    @Test
    public void myParticipantList_shouldDisplayMessageWhenEmptyRoom() {
        //Remove item at position 1 performing click on delete icon
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        //Perform a click to filter list for an empty room
        //Check if empty message is displayed at screen
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
        //Check if message is displayed

    }

}
