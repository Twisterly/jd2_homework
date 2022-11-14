package masha.cha.util;

import masha.cha.dao.PersonDaoImpl;
import masha.cha.pojos.Person;

import java.util.Scanner;

public class ScannerManager {

    Scanner console = new Scanner(System.in);
    LineManager lineManager = new LineManager();
    PersonDaoImpl personDaoImpl = new PersonDaoImpl();
    Person person = new Person();
    String line = null;

    public void startConsole() {
        System.out.println("What do you want to do with Person?");
        System.out.println("1 - SAVE person");
        System.out.println("2 - SAVE person WITH TRIGGER");
        System.out.println("3 - FIND person by ID");
        System.out.println("4 - DELETE person by ID");
        System.out.println("5 - GET person by ID");
        System.out.println("6 - LOAD person by ID");
        System.out.println("7 - UPDATE person by ID");
        System.out.println("8 - UPDATE person WITH TRIGGER");
        System.out.println("9 - EXIT");
        System.out.println("Insert:");

        int num = lineManager.checkInteger(console.next());
        switch (num) {

            case 1:
                save();
                break;
            case 2:
                saveWithTrigger();
                break;
            case 3:
                find();
                break;
            case 4:
                deleteById();
                break;
            case 5:
                get();
                break;
            case 6:
                load();
                break;
            case 7:
                update();
                break;
            case 8:
                updateWithTrigger();
                break;
            case 9:
                closeConsole();
                System.exit(0);
        }
        startNewTransaction();

    }

    public void startNewTransaction() {
        System.out.println("Do yo want one more transaction? Y/N");
        line = console.next();
        String answer = lineManager.checkYesOrNo(line);
        if (answer.equalsIgnoreCase("y")) {
            startConsole();
        } else if (answer.equalsIgnoreCase("n")) {
            System.out.println("Have a nice day! Goodbye!");
        } else {
            System.out.println("Please, try again");
            startConsole();
        }
    }


    public void closeConsole() {
        System.out.println("Have a nice day! Goodbye!");
        console.close();
    }

    public void save() {
        System.out.println("Insert person age");
        line = console.next();
        person.setAge(lineManager.checkAge(line));
        System.out.println("Insert person name with Capital letter");
        line = console.next();
        person.setName(lineManager.checkString(line));
        System.out.println("Insert person surname with Capital letter");
        line = console.next();
        person.setSurname(lineManager.checkString(line));
        personDaoImpl.savePerson(person);
        System.out.println(person.toString() + " was successfully saved");

    }


    public void saveWithTrigger() {
        System.out.println("Insert person age");
        line = console.next();
        person.setAge(lineManager.checkAge(line));
        System.out.println("Insert person name with Capital letter");
        line = console.next();
        person.setName(lineManager.checkString(line));
        System.out.println("Insert person surname with Capital letter");
        line = console.next();
        person.setSurname(lineManager.checkString(line));
        person = personDaoImpl.savePersonWithTrigger(person);
        System.out.println(person.toString() + " was successfully saved");
    }


    public void find() {
        System.out.println("Insert person id");
        line = console.next();
        int id = lineManager.checkInteger(line);
        person = personDaoImpl.findById(Long.valueOf(id));
        if (person == null) {
            System.out.println("Person does not exist");
        } else
            System.out.println(person.toString());
    }

    public void deleteById() {
        System.out.println("Insert person id");
        line = console.next();
        long id = Long.valueOf(lineManager.checkId(line));
        System.out.println(personDaoImpl.getById(id));
        person = personDaoImpl.deletePerson(id);
        System.out.println(person.toString() + " was successfully deleted");
    }


    public void get() {
        System.out.println("Insert person id");
        line = console.next();
        long id = Long.valueOf(lineManager.checkInteger(line));
        person = personDaoImpl.getById(id);
        if (person == null) {
            System.out.println("Person does not exist");
        } else {
            System.out.println(person.toString());
        }

    }

    public void load() {
        System.out.println("Insert person id");
        line = console.next();
        long id = Long.valueOf(lineManager.checkInteger(line));
        person = personDaoImpl.loadById(id);
        try {
            System.out.println(person.toString());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: Person does not " +
                    "exist");
        }
    }

    public Person createUpdatePerson() {
        System.out.println("Insert person id");
        line = console.next();
        long id = Long.valueOf(lineManager.checkId(line));
        person = personDaoImpl.findById(id);
        System.out.println("What do you want to update?");
        System.out.println("1 - update AGE");
        System.out.println("2 - update NAME");
        System.out.println("3 - update SURNAME");
        line = console.next();
        int num = lineManager.checkUpdateMenu(line);
        if (num == 1) {
            System.out.println("Insert new age:");
            line = console.next();
            person.setAge(lineManager.checkAge(line));
        } else if (num == 2) {
            System.out.println("Insert update name with Capital letter:");
            line = console.next();
            person.setName(lineManager.checkString(line));
        } else if (num == 3) {
            System.out.println("Insert update surname with Capital letter:");
            line = console.next();
            person.setSurname(lineManager.checkString(line));
        }
        return person;

    }

    public void update() {
        personDaoImpl.updatePerson(createUpdatePerson());
        System.out.println("Person was updated: " + person.toString());
    }

    public void updateWithTrigger() {
        person = personDaoImpl.updatePersonWithTrigger(createUpdatePerson());
        System.out.println("Person was updated with TRIGGER: " + person.toString());

    }


}