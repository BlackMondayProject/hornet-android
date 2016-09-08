package pl.blackmonday.hornet.ui;

import android.app.Application;
import android.content.Context;

import pl.blackmonday.hornet.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class App extends Application{

    private static Context appContext;

    public static Context context(){
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initThirdPartyLibraries();
    }

    private void initThirdPartyLibraries(){
        initCalligraphy();
    }

    private void initCalligraphy(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/makhina.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
