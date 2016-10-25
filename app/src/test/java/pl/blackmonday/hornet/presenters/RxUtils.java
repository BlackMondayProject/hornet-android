package pl.blackmonday.hornet.presenters;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by Marcin Laskowski on 10.10.16.
 * Senfino 2016
 */


public class RxUtils {

    public static void setUp(){
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    public static void tearDown(){
        RxAndroidPlugins.getInstance().reset();
    }

}
