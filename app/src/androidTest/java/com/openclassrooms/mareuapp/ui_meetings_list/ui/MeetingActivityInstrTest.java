package com.openclassrooms.mareuapp.ui_meetings_list.ui;

import android.widget.DatePicker;
import android.widget.TextView;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareuapp.R;
import com.openclassrooms.mareuapp.di.DI;
import com.openclassrooms.mareuapp.utils.DeleteViewAction;

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
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.mareuapp.R.string.ActionBarAddMeeting;
import static com.openclassrooms.mareuapp.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
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


    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        //Check if list of meeting is displayed
        onView(allOf(isDisplayed(), ViewMatchers.withId(R.id.list_meetings)))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        //Check the number of elements: 3
        int ITEMS_COUNT = DI.getMeetingApiService().getMeetings().size();
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Remove item at position 1 performing click on delete icon
        onView(allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        //Check the number of elements: 3-1 = 2
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myMeetingsList_AddMeetingAction_shouldDisplayAddMeetingPage() {
        //Perform a click to open add meeting activity
        onView(withId(R.id.fab_add_meeting)).perform(click());
        //Check action bar to see if we are in AddMeetingActivity
        onView(allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText(ActionBarAddMeeting)));
    }

    @Test
    public void myParticipantsList_filterAction_shouldDisplayAllMeetings() {
        int ITEMS_COUNT = DI.getMeetingApiService().getMeetings().size();
        //Check that we have all meetings by checking list's size
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
        //Perform a click on submenu
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
        //Check we filtered the list
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(1));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
        //Perform a click to get all meetings
        onView(withText("Toutes mes réunions")).perform(click());
        //Check the list has same size again
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
    }

    @Test
    public void myParticipantsList_filterAction_shouldDisplayMeetingsForGivenDate() {
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
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
    public void myParticipantsList_shouldDisplayNoMeetingsWhenEmptyDate() {
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
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
    public void myParticipantList_shouldDisplayMeetingsForGivenRoom() {
        int ITEMS_COUNT = DI.getMeetingApiService().getMeetings().size();
        //Ensure that we have all meetings by checking list's size
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
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
    public void myParticipantList_shouldDisplayNoMeetingsWhenEmptyRoom() {
        //Remove item with room Peach
        onView(allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        //Perform a click on main menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity().getApplicationContext());
        //Perform a click on submenu "Filtrer par Lieu"
        onView(withText("Filtrer par Lieu")).perform(click());
        //Perform a click on filter by Peach room
        onView(withText("Salle Peach")).perform(click());
        //Check if list is empty
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(0));
    }
}
