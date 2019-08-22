package com.lawerance.bakingapp;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
public ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);
    @Test
    public void recycleViewFirstItem() {
        onView(withId(R.id.rv_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_food_name)).check(matches(withText("Nutella Pie")));
    }
    @Test
    public void recycleViewSecondItem() {
        onView(withId(R.id.rv_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.tv_food_name)).check(matches(withText("Brownies")));
    }
    @Test
    public void recycleViewThirdItem() {
        onView(withId(R.id.rv_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.tv_food_name)).check(matches(withText("Yellow Cake")));
    }
    @Test
    public void recycleViewFourthItem() {
        onView(withId(R.id.rv_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.tv_food_name)).check(matches(withText("Cheesecake")));
    }
}
