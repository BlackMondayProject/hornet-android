package pl.blackmonday.hornet.domain.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin Laskowski on 2015-11-27.
 * Senfino 2015
 */

public class SharedPreferencesStorage {

    private final Gson gson;
    private final SharedPreferences prefs;

    public SharedPreferencesStorage(Context context, Gson gson) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }

    //==============================================================================================
    // STRING
    //==============================================================================================

    public String getString(String name) {
        return prefs.getString(name, null);
    }

    public void saveString(String name, String value) {
        prefs.edit().putString(name, value).apply();
    }

    public boolean containsString(String name) {
        return prefs.contains(name);
    }

    public void removeString(String name){
        prefs.edit().remove(name).apply();
    }

    //==============================================================================================
    // BOOLEAN
    //==============================================================================================

    public boolean getBoolean(String name) {
        return prefs.getBoolean(name, false);
    }

    public void saveBoolean(String name, boolean value) {
        prefs.edit().putBoolean(name, value).apply();
    }

    public boolean containsBoolean(String name) {
        return prefs.contains(name);
    }

    public void removeBoolean(String name) {
        prefs.edit().remove(name).apply();
    }

    //==============================================================================================
    // INTEGER
    //==============================================================================================

    public int getInt(String name) {
        return prefs.getInt(name, 0);
    }

    public void saveInt(String name, int value) {
        prefs.edit().putInt(name, value).apply();
    }

    public boolean containsInt(String name) {
        return prefs.contains(name);
    }

    public void removeInt(String name) {
        prefs.edit().remove(name).apply();
    }

    //==============================================================================================
    // OBJECT
    //==============================================================================================

    public <T> T getObject(String name, Class<T> clazz) {
        String value = getString(name);
        if (value != null) {
            try {
                return gson.fromJson(value, clazz);
            } catch (Exception e){
                removeObject(name); // delete if invalid
                return null;
            }
        } else {
            return null;
        }
    }

    public <T> void saveObject(String name, T object) {
        String value = gson.toJson(object);
        saveString(name, value);
    }

    public boolean containsObject(String name) {
        return prefs.contains(name);
    }

    public void removeObject(String name) {
        prefs.edit().remove(name).apply();
    }

    //==============================================================================================
    // LIST
    //==============================================================================================

    public <T> List<T> getList(String name, Class<T> clazz) {
        String value = getString(name);
        if (value != null) {
            try {
                return gson.fromJson(value, new ListOfJson<>(clazz));
            } catch (Exception e){
                removeList(name); // delete if invalid
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    public void saveList(String name, List<?> object) {
        String value = gson.toJson(object);
        saveString(name, value);
    }

    public void containsList(String name) {
        prefs.contains(name);
    }

    public void removeList(String name) {
        prefs.edit().remove(name).apply();
    }

    //==============================================================================================
    // HELPER
    //==============================================================================================

    private class ListOfJson<T> implements ParameterizedType { // for list parsing
        private Class<?> wrapped;

        ListOfJson(Class<T> wrapper) {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
