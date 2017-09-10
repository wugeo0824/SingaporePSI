package com.xjcrepe.sgpsi.map;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xjcrepe.sgpsi.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onLaunch_fetchesPsiReadings_showsO3SubIndex() throws Exception {
        String o3SubIndex = InstrumentationRegistry
                .getTargetContext()
                .getResources()
                .getStringArray(R.array.psi_readings_types)[0];

        onView(withId(R.id.spinner)).check(matches(withSpinnerText(o3SubIndex)));
        onView(withId(R.id.tvNorth)).check(matches(withText("14")));
        onView(withId(R.id.tvEast)).check(matches(withText("13")));
        onView(withId(R.id.tvSouth)).check(matches(withText("11")));
        onView(withId(R.id.tvWest)).check(matches(withText("14")));
        onView(withId(R.id.tvCentral)).check(matches(withText("12")));
    }

    @Test
    public void onLaunch_userSelectsPSIDaily_showsPSIDaily() throws Exception {
        String PSIDaily = InstrumentationRegistry
                .getTargetContext()
                .getResources()
                .getStringArray(R.array.psi_readings_types)[8];

        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(PSIDaily))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(PSIDaily)));
        onView(withId(R.id.tvNorth)).check(matches(withText("59")));
        onView(withId(R.id.tvEast)).check(matches(withText("56")));
        onView(withId(R.id.tvSouth)).check(matches(withText("56")));
        onView(withId(R.id.tvWest)).check(matches(withText("61")));
        onView(withId(R.id.tvCentral)).check(matches(withText("54")));
    }
}
