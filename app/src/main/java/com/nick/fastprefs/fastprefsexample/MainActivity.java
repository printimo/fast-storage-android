package com.nick.fastprefs.fastprefsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nick.fastprefs.faststorage.FastStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //preferences initialization
        FastStorage.init(getApplicationContext(),"1");

        final Person person1 = new Person(1,"James", "Armstrong","https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", 40);
        final Person person2 = new Person(2, "Nick", "Sherbina","https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", 40);
        PersonsResponse personsResponse = new PersonsResponse();
        personsResponse.setArray(new ArrayList<Person>() {{
            add(person1);
            add(person2);
        }});

        //save data into preferences
        FastStorage.save(personsResponse);

        //save data with special key
        FastStorage.save("city_1", "Kiev");
        FastStorage.save("city_2", "New York");
        FastStorage.save("city_3", "Amsterdam");
        FastStorage.save("city_4", "Berlin");

        Person anotherPerson = new Person(3, "Vasia", "Pupkin", "http://xxx", 34);
        Person anotherPerson2 = new Person(4, "Mongoberry", "Bernz", "http://xxx", 34);

        FastStorage.save("worker_1", anotherPerson);
        FastStorage.save("worker_2", anotherPerson2);


        //get data from preferences
        Log.d("o_O" , FastStorage.get(PersonsResponse.class).toString());

        //get data with specific id
        Log.d("o_O" , FastStorage.get("city_1") + " " + FastStorage.get("city_2") + " "
                + FastStorage.get("city_3") + " " + FastStorage.get("city_4"));

        Log.d("o_O", "key worker_1 : " + FastStorage.get("worker_1",Person.class).toString() + "\n" +
                "key worker_2 : " + FastStorage.get("worker_2",Person.class));

        //update data
        FastStorage.update(PersonsResponse.class, persons -> {
                Person person3 = new Person(5, "Kate", "Govzman", "http://xxx", 34);
                persons.getArray().add(person3);
            });

        FastStorage.update("worker_1", Person.class, person -> {
            person.setName("Petia");
        });

        Log.d("o_O" , FastStorage.get(PersonsResponse.class).toString());
        Log.d("o_O" , "person_1 after update : " + FastStorage.get("worker_1",Person.class).toString());

    }
}
