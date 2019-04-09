package com.example.androidcodelabapp.view;


import com.example.androidcodelabapp.R;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String JavaDeveloperUsername = "TheDancerCodes";
    private static final int ITEM_AT_POSITION = 20;
    private CountingIdlingResource countingIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    public void registerIdlingResource(){
        countingIdlingResource = mActivityTestRule.getActivity().getCountingIdlingResource();
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @Test
    public void mainActivityLayoutIsRendered() {
        onView(withId(R.id.cordinator)).check(matches(isDisplayed()));

    }

    @Test
    public void swipeToRefreshFunctionsProperly() {
        onView(withId(R.id.swipe)).perform(swipeDown());

    }


    @Test
    public void checkPresenceOfSomeDeveloper() {
        onView(withText("moseskamira")).check(doesNotExist());

    }



    @Test
    public void recyclerViewIsConnectedToMainActivity() throws Exception{
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
    }
    @Test
    public void onUserClichDisplaysDetailView()throws Exception{
        registerIdlingResource();
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(JavaDeveloperUsername)), click()));
    }

    @Test
    public void lastItemNotDisplayedOnScreen(){
        onView(withText("item: 29")).check(doesNotExist());
    }

    @Test
    public void itemInPositionTwenty() {
        registerIdlingResource();
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(scrollToPosition(ITEM_AT_POSITION));

    }

    @Test
    public void backButtonOnClick() throws Exception {
        registerIdlingResource();

        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(JavaDeveloperUsername)), click()));
        onView(withId(R.id.gitusername)).check(matches(isDisplayed()));
    }
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(mActivityTestRule.getActivity().getCountingIdlingResource());
    }

}
