package masha.cha.dao;

import lombok.SneakyThrows;
import masha.cha.pojos.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;


public class PersonDaoImplTest extends BaseDaoTest {


    PersonDao targetObject;
    Connection conn = testMysqlJdbcDataSource.getConnection();

    @Before
    public void setUp() {
        targetObject = new PersonDaoImpl(testSessionFactory);

    }

    @After
    public void tearDown() {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        Person person = new Person(1L, 26, "Andrey", "Petrov");
        targetObject.savePerson(person);

        //When
        targetObject.findById(person.getId());

        //Then
        ResultSet rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(26, age);
        assertEquals("Andrey", name);
        assertEquals("Petrov", surname);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void savePerson() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person();
        person.setAge(27);
        person.setName("Ivan");
        person.setSurname("Petrov");

        //When
        targetObject.savePerson(person);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "person;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void deletePerson() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);

        Person person = new Person(200L, 30, "Kate", "Rater");
        targetObject.savePerson(person);

        //When
        targetObject.deletePerson(person.getId());

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "person;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void toUpperCasePersonName() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person();
        person.setAge(19);
        person.setName("Pavel");
        person.setSurname("Pavlov");
        targetObject.savePerson(person);

        //When
        targetObject.toUpperCasePersonName(person.getId());

        //Then
        rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        String name = rs.getString("name");
        assertEquals("PAVEL", name);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }


    @Test
    @SneakyThrows
    public void toLowerCasePersonSurname() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery("select count(*)" +
                " from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person();
        person.setAge(90);
        person.setName("Petr");
        person.setSurname("Petrov");
        targetObject.savePerson(person);

        //When
        targetObject.toLowerCasePersonSurname(person.getId());

        //Then
        rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        String name = rs.getString("name");
        assertEquals("petrov", name);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void saveAndDeletePerson() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person(23, "Alex", "Jones");

        //When
        targetObject.saveAndDeletePerson(person);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "person;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }


}
