package org.chsweb.innovationacademy.jacksonbottom;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by winikkc on 3/16/18.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    // Set activity monitors
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MapsActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor vtmonitor = getInstrumentation().addMonitor
            (VirtualTourActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchMapActivityOnButtonClick() {
        assertNotNull(mainActivity.findViewById(R.id.button_launch_map_activity));

        onView(withId(R.id.button_launch_map_activity)).perform(click());

        Activity mapsActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(mapsActivity);

        mapsActivity.finish();
    }

    @Test
    public void testLaunchVirtualTourOnButtonClick() {
        assertNotNull(mainActivity.findViewById(R.id.button_launch_virtual_tour));

        onView(withId(R.id.button_launch_virtual_tour)).perform(click());
        Activity virtualTourActivity = getInstrumentation().waitForMonitorWithTimeout(vtmonitor,
                5000);

        assertNotNull(virtualTourActivity);

        virtualTourActivity.finish();
    }

    @Test
    public void testLaunch() {
        View view = mainActivity.findViewById(R.id.rules_list_view);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}