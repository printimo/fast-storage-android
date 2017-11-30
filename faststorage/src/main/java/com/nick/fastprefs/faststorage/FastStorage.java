package com.nick.fastprefs.faststorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by root on 29/11/17.
 */

public class FastStorage {

    private static final String APP_VERSION = "version";

    protected  FastStorage instance;
    protected  SharedPreferences preferences;
    protected  Context context;
    protected  HashMap<Class,Object> cache;
    protected  HashMap<String,Object> keyCache;

    public FastStorage(Context context) {
        this.context = context;
    }

    protected HashMap<Class,Object> getCache() {
        if (cache == null) {
            cache = new HashMap<>();
        }
        return cache;
    }

    protected HashMap<String,Object> getKeyCache() {
        if (keyCache == null) {
            keyCache = new HashMap<>();
        }
        return keyCache;
    }

    // returns true if version need to be updated
    public boolean init(Context context,String version) {
        instance = new FastStorage(context);
        return checkVersion(version);
    }

    public boolean checkVersion(String newVersion) {
        String version = getInstance().getString(APP_VERSION,null);
        if (version == null) {
            getInstance().edit().putString(APP_VERSION, newVersion).apply();
            return false;
        }
        return !version.equals(newVersion);
    }

    public SharedPreferences getInstance() {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return preferences;
    }

    // objects with the same class will be overridden! use PreferenceCompatible or save(key,object)
    // if there are more than one usage of one class
    public void save(Object object) {
        String key;
        if (object instanceof PreferenceCompatible) {
            key = ((PreferenceCompatible) object).getPrefsKey();
        } else {
            key = object.getClass().getName();
        }
        getInstance().edit().putString(key, new Gson().toJson(object,object.getClass())).apply();
        getCache().put(object.getClass(),object);
    }

    public void save(String key, Object object) {
        getInstance().edit().putString(key,new Gson().toJson(object,object.getClass())).apply();
        getKeyCache().put(key,object);
    }

    public <T> T get(Class<T> tClass) {
        T result = (T) getCache().get(tClass);
        if (result == null) {
            result = new Gson().fromJson(getInstance().getString(tClass.getName(), ""), tClass);
            if (result!=null) {
                getCache().put(result.getClass(), result);
            }
        }
        return result;
    }

    public String get(String key) {
        return get(key,String.class);
    }

    public <T> T get(String key, Class<T> tClass) {
        T result;
        Object obj = getKeyCache().get(key);
        if (obj == null) {
            result = getFromMemory(key,tClass);
        } else {
            if (tClass.isInstance(obj)) {
                result = (T) obj;
                return result;
            } else {
                result = getFromMemory(key,tClass);
            }
        }
        if (result!=null) {
            getKeyCache().put(key, result);
        }
        return result;
    }

    public <T> T getFromMemory(String key, Class<T> tClass) {
        return new Gson().fromJson(getInstance().getString(key, ""), tClass);
    }

    public <T> boolean update(Class<T> tClass, Function<T> function) {
        Object objectToUpdate = get(tClass);
        if (objectToUpdate!=null) {
            function.response((T) objectToUpdate);
            save(objectToUpdate);
            return true;
        }
        return false;
    }

    public <T> boolean update(String key, Class<T> tClass, Function<T> function) {
        Object objectToUpdate = get(key,tClass);
        if (objectToUpdate!=null && tClass.isInstance(objectToUpdate)) {
                function.response((T) objectToUpdate);
                save(key,objectToUpdate);
            return true;
        }
        return false;
    }

}
