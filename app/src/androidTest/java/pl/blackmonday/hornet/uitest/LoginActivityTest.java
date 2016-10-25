package pl.blackmonday.hornet.uitest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.ui.screens.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Marcin Laskowski on 17.08.16.
 * Senfino 2016
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void setLoginAndPassword(){
        onView(withHint(R.string.activityLogin_loginHint)).perform(typeText("test"));
        onView(withHint(R.string.activityLogin_passwordHint)).perform(typeText("test"));
        onView(withText(R.string.activityLogin_done)).perform(click());
    }

}
