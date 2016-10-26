package pl.blackmonday.hornet.ui.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import org.parceler.Parcels;

import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.ui.screens.bug.BugActivity;
import pl.blackmonday.hornet.ui.screens.home.HomeActivity;
import pl.blackmonday.hornet.ui.screens.login.LoginActivity;

/**
 * Created by Marcin Laskowski on 26.09.16.
 * Senfino 2016
 */

//@ParcelClass( // FIXME
//        value = DateTime.class,
//        annotation = @Parcel(converter = JodaDateTimeConverter.class))
public class ActivityNavigator implements Navigator {

    private final Activity activity;

    public ActivityNavigator(Activity activity) {
        this.activity = activity;
    }

    //==============================================================================================
    // KEYS
    //==============================================================================================

    private static final String KEY_BUG = "bug";

    //==============================================================================================
    // ACTIVITY NAVIGATION
    //==============================================================================================

    //-COMMON---------------------------------------------------------------------------------------

    @Override
    public void navigateUp() {
        this.activity.onNavigateUp();
    }

    //-LOGIN-ACTIVITY-------------------------------------------------------------------------------

    @Override
    public void goToLoginScreen() {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    //-HOME-ACTIVITY--------------------------------------------------------------------------------

    @Override
    public void goToHomeScreen() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    //-BUG-ACTIVITY---------------------------------------------------------------------------------

    @Override
    public void goToBugScreen(Bug bug) {
        Intent intent = new Intent(activity, BugActivity.class);
        intent.putExtra(KEY_BUG, Parcels.wrap(bug));
        activity.startActivity(intent);
    }

    @Override
    public Bug getBug() {
        Parcelable bug = activity.getIntent().getParcelableExtra(KEY_BUG);
        return Parcels.unwrap(bug);
    }

}
