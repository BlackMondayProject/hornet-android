package pl.blackmonday.hornet.domain.storage;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by Marcin Laskowski on 27.09.16.
 * Senfino 2016
 */


public class AppStorage implements IStorage {

    private SharedPreferencesStorage storage;

    public AppStorage(Context context, Gson gson){
        storage = new SharedPreferencesStorage(context, gson);
    }

    @Override
    public boolean containsCredentials() {
        return false;
    }

}
