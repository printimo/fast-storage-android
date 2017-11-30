package com.nick.fastprefs.fastprefsexample;

import com.nick.fastprefs.fastprefs.PreferenceCompatible;

/**
 * Created by root on 30/11/17.
 */

public class Person {

    private int uniqueId;

    private String name;

    private String surname;

    private String imageUrl;

    private int age;

    public Person(int id, String name, String surname, String imageUrl, int age) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.age = age;
        uniqueId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uniqueId=" + uniqueId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", age=" + age +
                '}';
    }


}
