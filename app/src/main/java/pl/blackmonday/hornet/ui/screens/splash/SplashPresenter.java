package pl.blackmonday.hornet.ui.screens.splash;

import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BasePresenter;

/**
 * Created by Marcin Laskowski on 27.09.16.
 * Senfino 2016
 */


public class SplashPresenter
        extends BasePresenter<SplashUi> {

    public SplashPresenter(SplashUi ui, Navigator navigator) {
        super(ui, navigator);
    }

    //==============================================================================================
    // LIFECYCLE
    //==============================================================================================

    @Override
    public void onCreate() {
        super.onCreate();
        navigator.goToHomeScreen();
    }

}
