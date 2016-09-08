package pl.blackmonday.hornet;

import android.util.Log;

import pl.blackmonday.hornet.api.client.Client;
import pl.blackmonday.hornet.api.endpoints.MainServerInterface;
import pl.blackmonday.hornet.api.retrofit.Logger;
import pl.blackmonday.hornet.api.retrofit.RetrofitClient;
import pl.blackmonday.hornet.domain.Api;
import pl.blackmonday.hornet.domain.IApi;

/**
 * Created by Marcin Laskowski on 17.08.16.
 * Senfino 2016
 */

public class Dependencies {

    private static volatile IApi api;

    private Dependencies() {
        // no constuctor
    }

    public static IApi instanceOfApi() {
        if (api == null) {
            synchronized (IApi.class) {
                if (api == null) {
                    MainServerInterface serverInterface = getServerInterface();
                    api = new Api(new Client(serverInterface));
                }
            }
        }
        return api;
    }

    private static MainServerInterface getServerInterface(){
        return new RetrofitClient()
                .setLogger(new Logger() {
                    @Override
                    public void log(String text) {
                        Log.d("API", text);
                    }
                })
                .setUrl(Config.MAIN_SERVER_URL)
                .create();
    }

}
