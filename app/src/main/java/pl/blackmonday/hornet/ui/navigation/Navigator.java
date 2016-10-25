package pl.blackmonday.hornet.ui.navigation;

import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 26.09.16.
 * Senfino 2016
 */


public interface Navigator {

    void goToLoginScreen();

    void goToHomeScreen();

    void goToBugScreen(Bug bug);

    Bug getBug();


}
