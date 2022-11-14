package by.masha.cha.dao;

import by.masha.cha.model.Person;

public class PersonDAO {

    public void print(Person person) {
        System.out.println(person.getName() + " " + person.getSecondName() +
                " " + person.getAge());
    }
}
