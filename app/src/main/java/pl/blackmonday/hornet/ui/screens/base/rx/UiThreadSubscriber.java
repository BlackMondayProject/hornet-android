package pl.blackmonday.hornet.ui.screens.base.rx;

import pl.blackmonday.hornet.ui.screens.base.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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
    public void onCompleted() {
        // not used
    }

    @Override
    public void onError(Throwable t) {
        checkFlag();
        if (presenter.isDestroyed()) return;
        Observable.error(t)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::nothing,
                        e -> {
                            raiseFlag();
                            error.onError(e);
                            always.always();
                        },
                        this::nothing
                );
    }

    @Override
    public void onNext(T t) {
        checkFlag();
        if (presenter.isDestroyed()) return;
        Observable.just(t)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        action ->{
                            raiseFlag();
                            done.onDone(t);
                        },
                        this::nothing,
                        always::always);
    }


    private void nothing() {
        //nothing
    }

    private void nothing(Object o) {
        //nothing
    }

    private void checkFlag(){
        if (oneUseFlag) throw new IllegalStateException("This object can be used only once");
    }

    private void raiseFlag(){
        oneUseFlag = true;
    }

}

