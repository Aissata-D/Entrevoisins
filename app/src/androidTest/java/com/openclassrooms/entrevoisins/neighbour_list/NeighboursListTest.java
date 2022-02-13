package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);
    private ListNeighbourActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myNeighboursList_click_shouldOpenNeighbourActivity() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbours().get(3);
        //On performe un clic à la position 3 sur la vue list_neighbours
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        // NeighbourDetail sreen is display
        onView(ViewMatchers.withId(R.id.neigbourg_details_main))
                .check(matches(isDisplayed()));
    }

    @Test
    public void myNeighboursList_click_TextView_name_is_not_empty() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbours().get(3);
        //On performe un clic à la position 3 sur la vue list_neighbours
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        //Check that the name is the right one
        onView(ViewMatchers.withId(R.id.neigbourg_details_name_text))
                .check(matches(withText(neighbour.getName())));
    }

    @Test
    public void myNeighboursFavory_display_only_favoryNeighbour_for_all_neighbour() {
        List<Neighbour> NeighbourList = DI.getNeighbourApiService().getNeighbours();
        int halfSize = (NeighbourList.size()) / 2;
        //On ajoute la moitié des voisins à la liste favory
        for (int i = 0; i < halfSize; i++) {
            onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            //Clic on floating button
            onView(allOf(ViewMatchers.withId(R.id.neigbourg_details_favory_button), isDisplayed()))
                    .perform(click());
            pressBack();
        }
        onView(withId(R.id.container)).perform(swipeLeft());

        for (int i = 0; i < halfSize; i++) {
            onView(allOf(ViewMatchers.withId(R.id.item_list_name), withText(NeighbourList.get(i).getName()),
                    isDisplayed()))
                    .check(matches(withText(NeighbourList.get(i).getName())));
        }
        for (int i = halfSize; i < NeighbourList.size(); i++) {
            onView(allOf(ViewMatchers.withId(R.id.item_list_name), withText(NeighbourList.get(i).getName()),
                    isDisplayed()))
                    .check(doesNotExist());
        }
        
    }
    /* @Test
    public void myNeighboursFavory_display_only_favoryNeighbour() {
        List<Neighbour> NeighbourList = DI.getNeighbourApiService().getNeighbours();
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbours().get(2);
        //On ajoute le voisins à la position 2 à la liste favory

        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        //Clic on floating button
        onView(allOf(ViewMatchers.withId(R.id.neigbourg_details_favory_button), isDisplayed()))
                .perform(click());
        pressBack();
        //On bascule sur le view Favory
        onView(withId(R.id.container)).perform(swipeLeft());
        //On verie que la liste favory contient le voisin à la position 2
        onView(allOf(ViewMatchers.withId(R.id.item_list_name), withText("Chloé"),
                isDisplayed()))
                .check(matches(withText(neighbour.getName())));
        //On verie que la liste favory ne contient pas le voisin à la position 3
        onView(allOf(ViewMatchers.withId(R.id.item_list_name), withText("Vincent"),
                isDisplayed()))
                .check(doesNotExist());
    }*/


}