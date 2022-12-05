package by.masha.cha.model;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    @SneakyThrows
    public void testCreatePerson() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");

        Person person1 = context.getBean("person1", Person.class);
        Person person2 = context.getBean("person2", Person.class);
        assertEquals("Alex", person1.getName());
        assertEquals("Williams", person1.getSurname());
        assertEquals("Perkins", person2.getSurname());
        assertNotNull(person1.getAddress());
        assertNotNull(person2.getAddress());
        assertEquals("Austria", person1.getAddress().getCountry());
        assertEquals("Warsaw", person2.getAddress().getCity());
        assertEquals("Schmalzhofgasse",
                person1.getAddress().getAddressList().get(3));
        assertEquals(16, person1.getAddress().getAddressList().get(4));
        assertEquals(239800, person2.getAddress().getAddressList().get(3));
        context.close();
    }

}



