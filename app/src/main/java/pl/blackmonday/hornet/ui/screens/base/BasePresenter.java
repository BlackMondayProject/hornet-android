package pl.blackmonday.hornet.ui.screens.base;

import android.util.Log;

import pl.blackmonday.hornet.domain.api.parsing.ParserException;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public abstract class BasePresenter<Ui extends BaseUi> {

    protected Ui ui;
    protected Navigator navigator;

    public BasePresenter(Ui ui, Navigator navigator) {
        this.ui = ui;
        this.navigator = navigator;
    }

    //==============================================================================================
    // LIFECYCLE
    //==============================================================================================

    public void onCreate() {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        ui = null;
    }

    public boolean isDestroyed() {
        return ui == null;
    }

    public boolean onBackPressed() {
        return false; // don't intercept back pressing
    }

    //==============================================================================================
    // ERROR HANDLING
    //==============================================================================================

    protected void handleError(Throwable e) {
        handleKnownErrors(e);
    }

    protected void handleError(Throwable e, ErrorHandler handler) {
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            boolean isHandled = handler.handle(exception.code());
            if (isHandled) return;
        }
        handleKnownErrors(e);
    }

    private void handleKnownErrors(Throwable e) {
        if (e instanceof ParserException) {
            Log.e("ERROR", "PARSER", e);
        } else {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void onUpClicked() {
        navigator.navigateUp();
    }

    protected interface ErrorHandler {

        boolean handle(int errorCode);

    }

}
