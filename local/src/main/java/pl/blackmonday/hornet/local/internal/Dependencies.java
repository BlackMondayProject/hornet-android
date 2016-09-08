package pl.blackmonday.hornet.local.internal;

import pl.blackmonday.hornet.api.client.Client;
import pl.blackmonday.hornet.api.client.IClient;
import pl.blackmonday.hornet.api.endpoints.MainServerInterface;
import pl.blackmonday.hornet.api.retrofit.RetrofitClient;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class Dependencies {

    private static final String APIARY_URL = "https://private-7936d-hornet1.apiary-mock.com";

    public static IClient instanceOfClient(){
        MainServerInterface mainServerInterface = new RetrofitClient()
                .setLogger(new ConsoleLogger())
                .setUrl(APIARY_URL)
                .create();

        return new Client(mainServerInterface);
    }

}
