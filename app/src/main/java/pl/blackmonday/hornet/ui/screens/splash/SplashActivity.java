package pl.blackmonday.hornet.ui.screens.splash;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BaseActivity;

/**
 * Created by Marcin Laskowski on 27.09.16.
 * Senfino 2016
 */


public class SplashActivity
        extends BaseActivity<SplashPresenter>
        implements SplashUi {

    //==============================================================================================
    // CREATION
    //==============================================================================================

    @Nullable
    @Override
    protected Integer provideLayoutId() {
        return null;
    }

    @NonNull
    @Override
    protected SplashPresenter providePresenter(Navigator navigator, IApi api) {
        return new SplashPresenter(this, navigator);
    }

}
