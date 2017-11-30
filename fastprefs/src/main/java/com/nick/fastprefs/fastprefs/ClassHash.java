package com.nick.fastprefs.fastprefs;

import java.util.HashMap;

/**
 * Created by root on 30/11/17.
 */

public class ClassHash<T> extends HashMap<Class,T> {

    public void put(T t) {
        super.put(t.getClass(),t);
    }

    public <T> T get(Class<T> tClass) {
        return (T) super.get(tClass);
    }

}
