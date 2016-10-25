package pl.blackmonday.hornet.ui;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.dependencies.AppComponent;
import pl.blackmonday.hornet.dependencies.AppModule;
import pl.blackmonday.hornet.dependencies.DaggerAppComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class App extends Application{

    @SuppressLint("StaticFieldLeak")
    private static Context appContext;

    public static Context context(){
        return appContext;
    }

    public static AppComponent dependencies(){
        return component;
    }

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initThirdPartyLibraries();
    }

    private void initThirdPartyLibraries(){
        initDagger();
        initJodaTime();
        initCalligraphy();
    }

    private void initDagger() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    private void initJodaTime() {
        JodaTimeAndroid.init(this);
    }

    private void initCalligraphy(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/makhina.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
