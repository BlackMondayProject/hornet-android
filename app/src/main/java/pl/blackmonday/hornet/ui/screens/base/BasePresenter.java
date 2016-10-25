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

    public BasePresenter(Ui ui, Navigator navigator){
        this.ui = ui;
        this.navigator = navigator;
    }

    void destroy() {
        onDestroy();
        ui = null;
    }

    public void onCreate() {
        // empty by default
    }

    public void onStart() {
        // empty by default
    }

    public void onResume() {
        // empty by default
    }

    public void onPause() {
        // empty by default
    }

    public void onStop() {
        // empty by default
    }

    public void onDestroy() {
        // empty by default
    }

    public boolean isDestroyed() {
        return ui == null;
    }

    protected void handleError(Throwable e) {
        handleKnownErrors(e);
    }

    protected void handleError(Throwable e, ErrorHandler handler) {
        if (e instanceof HttpException){
            HttpException exception = (HttpException) e;
            boolean isHandled = handler.handle(exception.code());
            if (isHandled) return;
        }
        handleKnownErrors(e);
    }

    private void handleKnownErrors(Throwable e) {
        if (e instanceof ParserException){
            Log.e("ERROR", "PARSER", e);
        } else {
            Log.e("ERROR", e.getMessage());
        }
    }

    protected interface ErrorHandler{

        boolean handle(int errorCode);

    }

}
