package pl.blackmonday.hornet.ui.screens.base;

import org.jdeferred.DoneCallback;
import org.jdeferred.DonePipe;
import org.jdeferred.FailCallback;
import org.jdeferred.FailPipe;
import org.jdeferred.Promise;

import pl.blackmonday.hornet.api.client.ApiError;
import pl.blackmonday.hornet.common.Promises;
import pl.blackmonday.hornet.domain.IApi;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public abstract class BasePresenter<Ui extends BaseUi> {

    protected Ui ui;
    protected IApi api;

    public BasePresenter(Ui ui, IApi api){
        this.ui = ui;
        this.api = api;
    }

    protected abstract class SafeDoneCallback<D> implements DoneCallback<D>{

        @Override
        public void onDone(D result) {
            if (ui != null){
                onSafeDone(result);
            }
        }

        public abstract void onSafeDone(D result);

    }

    protected abstract class SafeFailCallback<F> implements FailCallback<F> {

        @Override
        public void onFail(F result) {
            if (ui != null){
                onSafeFail(result);
            }
        }

        public abstract void onSafeFail(F result);

    }

    protected SafeFailCallback<ApiError> defaultFail(){
        return new SafeFailCallback<ApiError>() {
            @Override
            public void onSafeFail(ApiError result) {
                defaultFail(result);
            }
        };
    }

    protected void defaultFail(ApiError apiError){

    }

    protected <D, F, P> Promise<D, F, P> load(Promise<D, F, P> promise){
        showProgress();
        return promise.then(new DonePipe<D, D, F, P>() {
            @Override
            public Promise<D, F, P> pipeDone(D result) {
                if (ui != null) hideProgress();
                return Promises.success(result);
            }
        }, new FailPipe<F, D, F, P>() {
            @Override
            public Promise<D, F, P> pipeFail(F result) {
                if (ui != null) hideProgress();
                return Promises.fail(result);
            }
        });
    }

    private void showProgress(){

    }

    private void hideProgress(){

    }

}
