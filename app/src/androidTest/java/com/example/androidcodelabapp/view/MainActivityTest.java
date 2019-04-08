package com.example.androidcodelabapp.view;


import com.example.androidcodelabapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void swipeToRefreshFunctionsProperly() {
        onView(withId(R.id.swipe)).perform(swipeDown());

    }

    @Test
    public void checkPresenceOfSomeDeveloper() {
        onView(withText("moseskamira")).check(doesNotExist());

    }


    @Test
    public void recyclerViewIsConnectedToMainActivity() {
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void lastItemNotDisplayedOnScreen(){
        onView(withText("item: 29")).check(doesNotExist());
    }

}
