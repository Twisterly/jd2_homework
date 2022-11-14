package by.masha.cha.util;

import by.masha.cha.dao.PersonDAO;
import by.masha.cha.model.Person;

public class MyMain {

    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();
        Person person = new Person("Alla", "Petrova", 29);
        personDAO.print(person);
    }
}
