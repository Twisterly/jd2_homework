package masha.cha.loader;

import masha.cha.dao.PersonDaoImpl;
import masha.cha.pojos.Person;


public class PersonLoader {

    public static void main(String[] args) {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        personDaoImpl.savePerson(new Person(86, "Oleg", "Ivannikov"));
        System.out.println(personDaoImpl.getById(1L));
        System.out.println("Выполняем метод load: " + personDaoImpl
                .loadById(3L));
        System.out.println("Выполняем метод get: " + personDaoImpl.getById
                (4L));


    }

}
