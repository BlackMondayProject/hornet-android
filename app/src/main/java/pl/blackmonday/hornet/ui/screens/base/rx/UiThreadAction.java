package pl.blackmonday.hornet.ui.screens.base.rx;

import pl.blackmonday.hornet.ui.screens.base.BasePresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Marcin Laskowski on 25.10.16.
 * Senfino 2016
 */

public class UiThreadAction<T> implements Action1<T> {

    private BasePresenter presenter;
    private DoneCallback<T> callback;

    public UiThreadAction(BasePresenter presenter, DoneCallback<T> callback){
        this.presenter = presenter;
        this.callback = callback;
    }

    @Override
    public void call(T t) {
        Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .skipWhile(aVoid -> presenter.isDestroyed())
                .subscribe(aVoid -> callback.onDone(t));
    }

}
