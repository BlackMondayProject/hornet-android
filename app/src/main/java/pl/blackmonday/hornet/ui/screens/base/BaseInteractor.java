package pl.blackmonday.hornet.ui.screens.base;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.screens.base.rx.AlwaysCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.DoneCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.EmptyDoneCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.ErrorCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.UiThreadAction;
import pl.blackmonday.hornet.ui.screens.base.rx.UiThreadSubscriber;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Marcin Laskowski on 25.10.16.
 * Senfino 2016
 */

public class BaseInteractor<Presenter extends BasePresenter> {

    protected Presenter presenter;
    protected IApi api;

    public BaseInteractor(Presenter presenter, IApi api){
        this.presenter = presenter;
        this.api = api;
    }

    //==============================================================================================
    // UI HANDLERS
    //==============================================================================================

    protected  <T> Subscriber<T> onUi(DoneCallback<T> done, ErrorCallback error, AlwaysCallback always) {
        return new UiThreadSubscriber<>(presenter, done, error, always);
    }

    protected <T> Subscriber<T> onUi(EmptyDoneCallback done, ErrorCallback error, AlwaysCallback always) {
        DoneCallback<T> emptyDone = t -> done.onDone();
        return onUi(emptyDone, error, always);
    }

    protected  <T> Action1<T> onUi(DoneCallback<T> done) {
        return new UiThreadAction<>(presenter, done);
    }

}
