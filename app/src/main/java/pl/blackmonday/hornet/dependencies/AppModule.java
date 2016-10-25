package pl.blackmonday.hornet.dependencies;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.blackmonday.hornet.api.IServer;
import pl.blackmonday.hornet.api.client.RetrofitClient;
import pl.blackmonday.hornet.domain.api.Api;
import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.domain.storage.AppStorage;
import pl.blackmonday.hornet.domain.storage.IStorage;
import pl.blackmonday.hornet.ui.App;

/**
 * Created by Marcin Laskowski on 17.08.16.
 * Senfino 2016
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    IApi providesApi(IServer server) {
        return new Api(server);
    }

    @Provides
    IServer providesServerInterface() {
        return new RetrofitClient()
                .setLogger(text -> Log.d("API", text))
                .create();
    }

    @Provides
    @Singleton
    IStorage providesStorage(Context context) {
        return new AppStorage(context, new Gson());
    }

    @Provides
    Context providesContext(){
        return App.context();
    }

}
