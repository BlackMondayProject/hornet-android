package pl.blackmonday.hornet.ui.navigation;

import android.app.Activity;
import android.content.Intent;

import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.ui.screens.bug.BugActivity;
import pl.blackmonday.hornet.ui.screens.home.HomeActivity;
import pl.blackmonday.hornet.ui.screens.login.LoginActivity;

/**
 * Created by Marcin Laskowski on 26.09.16.
 * Senfino 2016
 */


public class ActivityNavigator implements Navigator {

    private static final String KEY_BUG = "bug";

    private final Activity activity;

    public ActivityNavigator(Activity activity){
        this.activity = activity;
    }

    @Override
    public void goToLoginScreen() {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToHomeScreen() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void goToBugScreen(Bug bug) {
        Intent intent = new Intent(activity, BugActivity.class);
        intent.putExtra(KEY_BUG, bug);
        activity.startActivity(intent);
    }

    @Override
    public Bug getBug(){
        return activity.getIntent().getParcelableExtra(KEY_BUG);
    }

}
