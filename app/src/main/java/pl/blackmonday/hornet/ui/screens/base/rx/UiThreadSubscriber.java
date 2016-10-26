package pl.blackmonday.hornet.ui.screens.base.rx;

import pl.blackmonday.hornet.ui.screens.base.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Marcin Laskowski on 25.10.16.
 * Senfino 2016
 */

public class UiThreadSubscriber<T> extends Subscriber<T> {

    private BasePresenter presenter;
    private final DoneCallback<T> done;
    private final ErrorCallback error;
    private final AlwaysCallback always;
    private boolean oneUseFlag = false;

    public UiThreadSubscriber(BasePresenter presenter, DoneCallback<T> done, ErrorCallback error, AlwaysCallback always) {
        this.presenter = presenter;
        this.done = done;
        this.error = error;
        this.always = always;
    }

    @Override
    public void onNext(T t) {
        subscribeToUi(aVoid -> {
            done.onDone(t);
            always.always();
        });
    }

    @Override
    public void onError(Throwable t) {
        subscribeToUi(aVoid -> {
            error.onError(t);
            always.always();
        });
    }

    @Override
    public void onCompleted() {
        // not used
    }

    private void subscribeToUi(Action1<Object> action) {
        Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(aVoid -> checkFlag())
                .skipWhile(aVoid -> presenter.isDestroyed())
                .subscribe(action);
    }

    private void checkFlag() {
        if (oneUseFlag) throw new IllegalStateException("This object can be used only once");
        oneUseFlag = true;
    }

}

