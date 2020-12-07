package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareuapp.R.string.ActionBarAddMeeting;
import static com.openclassrooms.mareuapp.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingActivityInstrTest {

    private Calendar mCalendar = Calendar.getInstance();
    private Date mDate = mCalendar.getTime();
    int ITEMS_COUNT = DI.getMeetingApiService().getMeetings().size();

    @Rule
    public ActivityScenarioRule mActivityRule =
            new ActivityScenarioRule<>(MeetingActivity.class);


    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        //Check if list of meeting is displayed
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_meetings)))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        //Check the number of elements: 3
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Remove item at position 1 performing click on delete icon
        onView(allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        //Check the number of elements: 3-1 = 2
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myMeetingsList_addMeetingAction_shouldDisplayAddMeetingPage() {
        //Perform a click to open add meeting activity
        onView(withId(R.id.fab_add_meeting)).perform(click());
        //Check action bar to see if we are in AddMeetingActivity
        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText(ActionBarAddMeeting)));
    }

    @Test
    public void myMeetingsList_filterAction_shouldDisplayAllMeetings() {
        //Check that we have all meetings by checking list's size
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
        //Check we filtered the list
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(1));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //Perform a click to get all meetings
        onView(withText("Toutes mes réunions")).perform(click());
        //Check the list has same size again
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
    }

    @Test
    public void myMeetingsList_filterAction_shouldDisplayMeetingsForGivenDate() {
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Date")).perform(click());
        //Select the date of Réunion A
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 12, 16));
        //Perform a click on Ok button
        onView(withText("OK")).perform(click());
        //Check  if list contains only one element
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(1));
        //Check if this meetings is Réunion A
        onView(allOf(isDisplayed(), withId(R.id.item_meeting_name))).check(matches(withText("Réunion A - 18:00 - Peach")));
    }

    @Test
    public void myMeetingsList_shouldDisplayNoMeetingsWhenEmptyDate() {
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Date")).perform(click());
        //Select an empty date, for example 18/10/2050
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2050, 10, 18));
        //Perform a click to confirm date filter
        onView(withText("OK")).perform(click());
        //Check if list is empty for the given date
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(0));
    }

    @Test
    public void myMeetingsList_shouldDisplayMeetingsForGivenRoom() {
        //Ensure that we have all meetings by checking list's size
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
        //Check if list contains only our element for the given room
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(1));
        //Check if its the good one
        onView(allOf(isDisplayed(), withId(R.id.item_meeting_name))).check(matches(withText("Réunion A - 18:00 - Peach")));
    }

    @Test
    public void myMeetingsList_shouldDisplayNoMeetingsWhenEmptyRoom() {
        //Remove item with room Peach
        onView(allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(getApplicationContext());
        //Perform a click on submenu "Filtrer par Lieu"
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
        //Check if list is empty
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(0));
    }

    @Test
    public void myMeetingsList_createAction_shouldCreateNewMeetingAndAddItToMeetings() {
        //Ensure we have all meetings checking meeting list's size
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Perform a click to open add meeting activity
        onView(withId(R.id.fab_add_meeting)).perform(click());
        //Insert name of meeting
        onView(Matchers.allOf(instanceOf(EditText.class), withId(R.id.meeting_topic))).perform(replaceText("Réunion X"));
        //Click on participant's emails
        onView(allOf(withId(R.id.email), withText("viviane@lamzone.com"))).perform(scrollTo(), click());
        onView(allOf(withId(R.id.email), withText("paul@lamzone.com"))).perform(click());
        //Click on the room choice
        onView(allOf(withId(R.id.room), withText("Luigi"))).perform(scrollTo(), click());
        //Click on validate meeting
        onView(allOf(withId(R.id.validate_meeting), withText("Valider la réunion"))).perform(scrollTo(), click());
        //Ensure we find one more meeting in the list
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT + 1));
        //Check Réunion X is displayed correctly
        ViewInteraction textView = onView(
                allOf(withId(R.id.item_meeting_name), withText(containsString("Réunion X")),
                        withParent(allOf(withId(R.id.item_list_container),
                                withParent(withId(R.id.list_meetings)))),
                        isDisplayed()));
        textView.check(matches(withText(containsString("Réunion X"))));
        //Check Participants are displayed correctly
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_meeting_mails), withText("viviane@lamzone.com, paul@lamzone.com"),
                        withParent(allOf(withId(R.id.item_list_container),
                                withParent(withId(R.id.list_meetings)))),
                        isDisplayed()));
        textView2.check(matches(withText("viviane@lamzone.com, paul@lamzone.com")));
    }
}