package org.chsweb.innovationacademy.jacksonbottom;

import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Created by winikkc on 3/16/18.
 */
public class VirtualTourActivityTest {
    @Rule
    public ActivityTestRule<VirtualTourActivity> mActivityVirtualTourTestRule = new
            ActivityTestRule<VirtualTourActivity>(VirtualTourActivity.class);
    private VirtualTourActivity mActivity = null;
    private GoogleMap mGMap = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityVirtualTourTestRule.getActivity();

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void testOnCreate() throws Exception {
        RelativeLayout rlContainer = (RelativeLayout) mActivity.findViewById(R.id.rLayout_virtual_tour_main);

        assertNotNull(rlContainer);

    }

    @Test
    public void testOnMapReady() throws Exception {
        UiDevice mDevice = UiDevice.getInstance(getInstrumentation());
        UiObject mMarker = mDevice.findObject(new UiSelector().descriptionContains(String.valueOf(R.string
                .marker_jackson_main)));
        assertNotNull(mMarker);

    }

    @Test
    public void testOnResumeFragments() throws Exception {
    }

    @Test
    public void testOnItemSelected() throws Exception {
    }

    @Test
    public void testOnInfoWindowClick() throws Exception {
    }

    @Test
    public void testOnMarkerClick() throws Exception {
    }

}