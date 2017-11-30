# fast-storage-android

code examples: 

        FastStorage fastStorage = new FastStorage(getApplicationContext());

        //preferences initialization
        
        //Person is a class that contains id, name, surname, imageUrl and age
        //PersonsResponse - array of persons 

        final Person person1 = new Person(1,"James", "Armstrong","https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", 40);
        final Person person2 = new Person(2, "Nick", "Sherbina","https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png", 40);
        PersonsResponse personsResponse = new PersonsResponse();
        personsResponse.setArray(new ArrayList<Person>() {{
            add(person1);
            add(person2);
        }});

        //save data into preferences
        fastStorage.save(personsResponse);

        //save data with special key
        fastStorage.save("city_1", "Kiev");
        fastStorage.save("city_2", "New York");
        fastStorage.save("city_3", "Amsterdam");
        fastStorage.save("city_4", "Berlin");

        Person anotherPerson = new Person(3, "Vasia", "Pupkin", "http://xxx", 34);
        Person anotherPerson2 = new Person(4, "Mongoberry", "Bernz", "http://xxx", 34);

        fastStorage.save("worker_1", anotherPerson);
        fastStorage.save("worker_2", anotherPerson2);


        //get data from preferences
        Log.d("o_O" , fastStorage.get(PersonsResponse.class).toString());

        //get data with specific id
        Log.d("o_O" , fastStorage.get("city_1") + " " + fastStorage.get("city_2") + " "
                + fastStorage.get("city_3") + " " + fastStorage.get("city_4"));

        Log.d("o_O", "key worker_1 : " + fastStorage.get("worker_1",Person.class).toString() + "\n" +
                "key worker_2 : " + fastStorage.get("worker_2",Person.class));

        //update data
        fastStorage.update(PersonsResponse.class, persons -> {
                Person person3 = new Person(5, "Kate", "Govzman", "http://xxx", 34);
                persons.getArray().add(person3);
            });

        fastStorage.update("worker_1", Person.class, person -> {
            person.setName("Petia");
        });

        Log.d("o_O" , fastStorage.get(PersonsResponse.class).toString());
        Log.d("o_O" , "person_1 after update : " + fastStorage.get("worker_1",Person.class).toString());


installation instructions:
[![](https://jitpack.io/v/printimo/fast-storage-android.svg)](https://jitpack.io/#printimo/fast-storage-android/1.0.2)
