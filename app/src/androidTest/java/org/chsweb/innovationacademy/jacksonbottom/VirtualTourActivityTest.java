package org.chsweb.innovationacademy.jacksonbottom;

import android.support.test.rule.ActivityTestRule;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by winikkc on 3/16/18.
 */
public class VirtualTourActivityTest {
    @Rule
    public ActivityTestRule<VirtualTourActivity> mActivityVirtualTourTestRule = new
            ActivityTestRule<VirtualTourActivity>(VirtualTourActivity.class);
    private VirtualTourActivity mActivity = null;

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

        //VirtualTourActivity fragment = new FragmentToTest();
        //mActivity.getSupportFragmentManager().findFragmentById(R.id.map);
    }

    @Test
    public void testOnMapReady() throws Exception {
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