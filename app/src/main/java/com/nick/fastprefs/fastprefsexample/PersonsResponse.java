package com.nick.fastprefs.fastprefsexample;

import java.util.List;

/**
 * Created by root on 30/11/17.
 */

public class PersonsResponse {

    private List<Person> array;

    public List<Person> getArray() {
        return array;
    }

    public void setArray(List<Person> array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "PersonsResponse{" +
                "array=" + array +
                '}';
    }
}
