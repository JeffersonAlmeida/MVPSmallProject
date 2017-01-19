package com.mvpsmallproject.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.mvpsmallproject.ActivityRule;
import com.mvpsmallproject.MVPApplication;
import com.mvpsmallproject.injection.DaggerServiceComponent;
import com.mvpsmallproject.injection.ServiceComponent;
import com.mvpsmallproject.util.MockServiceModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public final ActivityRule<MainActivity> mainActivity = new ActivityRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void showListOfRibots(){

        ServiceComponent testServiceComponent = DaggerServiceComponent.builder()
                .serviceModule(new MockServiceModule())
                .build();

        ((MVPApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext())
                .setServiceComponent(testServiceComponent);

        mainActivity.launchActivity();

        onView(withText("Jeff"))
                .check(matches(isDisplayed()));

    }

}