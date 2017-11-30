package com.nick.fastprefs.fastprefs;

/**
 * Created by root on 30/11/17.
 */

public interface PreferenceCompatible<T> {

    String getPrefsKey();

    String getKeyByData(T t);

}
