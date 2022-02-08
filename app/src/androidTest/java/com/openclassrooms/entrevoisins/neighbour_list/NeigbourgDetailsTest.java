package com.openclassrooms.entrevoisins.neighbour_list;

import com.openclassrooms.entrevoisins.R;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class NeigbourgDetailsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void greeterSaysHello() {
        // onView(withId(R.id.neigbourg_details_favory_button)).check(matches(withText("Add to favory" )));
        // onView(withId(R.id.name_field)).perform(typeText("Steve"));
        // onView(withId(R.id.neigbourg_details_favory_button)).perform(click());
        onView(withId(R.id.neigbourg_details_favory_button)).perform(click());
        // .check(matches(withText("Add to favory" )));
        //onView(withText("Hello Steve!")).check(matches(isDisplayed()));
    }
}