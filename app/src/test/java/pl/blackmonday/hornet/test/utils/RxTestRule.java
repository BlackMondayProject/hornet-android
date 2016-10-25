package pl.blackmonday.hornet.test.utils;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by Marcin Laskowski on 25.10.16.
 * Senfino 2016
 */

public class RxTestRule extends TestWatcher {

    @Override
    protected void starting(Description description) {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Override
    protected void finished(Description description) {
        RxAndroidPlugins.getInstance().reset();
    }

}
