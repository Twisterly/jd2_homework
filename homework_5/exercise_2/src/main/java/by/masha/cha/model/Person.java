package by.masha.cha.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

@Getter
@Setter
public class Person implements InitializingBean {

    private static final String DEFAULT_NAME = "Peter";
    private static final String DEFAULT_SURNAME = "Williams";
    private static final Integer DEFAULT_AGE = 30;

    private Integer id;
    private Integer age;
    private String name;
    private String surname;
    private AbstractAddress address;


    private Person() {
    }

    public static Person getPerson() {
        return new Person();
    }

    public String toString() {
        return "Person: " +
                " id = " + getId() +
                ", age = " + getAge() +
                ", name = " + getName() +
                ", surname = " + getSurname();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Call init() in Person.class ");
        System.out.println("Initialization bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (surname == null) {
            System.out.println("Using default surname");
            surname = DEFAULT_SURNAME;
        }
        if (age == null) {
            System.out.println("Using default age");
            age = DEFAULT_AGE;

        }
    }

    public void destroy() {
        System.out.println("Call destroy() in Peron.class");


    }
}

