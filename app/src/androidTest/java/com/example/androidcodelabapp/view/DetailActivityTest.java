package com.example.androidcodelabapp.view;


import com.example.androidcodelabapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> detailActivityTestRule = new ActivityTestRule<>(
            DetailActivity.class);
    @Rule
    public IntentsTestRule<DetailActivity> intentRule = new IntentsTestRule<>(DetailActivity.class,
            true, false);

    @Before
    public void startActivity() throws InterruptedException {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.GITHUB_USERS, "Oclemy");
        intentRule.launchActivity(intent);
        Thread.sleep(4000);

    }

    @Test
    public void detailActivityIsDisplayed() {
        onView(withId(R.id.detail_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void githubUrlTextIsDisplayed() {
        onView(withId(R.id.githolder)).check(matches(isDisplayed()));
    }

    @Test
    public void developerGithubUrl() {
        onView(withId(R.id.githuburl)).check(matches(isDisplayed()));
    }

    @Test
    public void organizationTextDisplayed() {
        onView(withId(R.id.orgholder)).check(matches(isDisplayed()));
    }

    @Test
    public void developerOrganizationDisplayed() {
        onView(withId(R.id.org)).check(matches(isDisplayed()));
    }

    @Test
    public void detailActivityDisplaysShareButton() {
        onView(withId(R.id.sharebutton)).check(matches(isDisplayed()));
    }


}
