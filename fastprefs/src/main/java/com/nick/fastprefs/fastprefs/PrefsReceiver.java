package com.nick.fastprefs.fastprefs;

public class PrefsReceiver<T> {

    private Class type;

    public PrefsReceiver(Class<T> type) {
        this.type = type;

    }

    public T get() {
        return (T) FastStorage.get(type);
    }

}