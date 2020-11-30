package com.openclassrooms.mareuapp.ui_meetings_list.ui;


import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareuapp.R;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(JUnit4.class)
public class AddMeetingInstrTest {

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityRule =
            new ActivityTestRule<>(AddMeetingActivity.class);

    @Before
    public void setUp() {
        AddMeetingActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    @Test
    public void addMeeting_checkBackToPreviousActivity() {
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        onView(Matchers.allOf(instanceOf(TextView.class),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText("Ma Réu")));
    }

    @Test
    public void addMeeting_checkMeetingNameInput() {
        onView(Matchers.allOf(instanceOf(EditText.class), withId(R.id.meeting_topic))).perform(replaceText("Réunion X"));
        onView(allOf(withId(R.id.email), withText("viviane@lamzone.com"))).perform(scrollTo(), click());
        onView(allOf(withId(R.id.email), withText("paul@lamzone.com"))).perform(click());
        onView(allOf(withId(R.id.room), withText("Luigi"))).perform(scrollTo(), click());
        onView(Matchers.allOf(instanceOf(EditText.class), withId(R.id.meeting_topic))).perform(replaceText("Réunion X"));
        onView(allOf(withId(R.id.validate_meeting), withText("Valider la réunion"))).perform(scrollTo(), click());
        Intents.init();
        mActivityRule.launchActivity(new Intent());
        intended(hasComponent(MeetingActivity.class.getName()));

        onView(allOf(withId(R.id.item_meeting_name),
                withParent(allOf(withId(R.id.item_list_container),
                        withParent(withId(R.id.list_meetings)))))).check(matches(withText("Réunion  - 10:03 - Luigi")));
        Intents.release();
    }
}

