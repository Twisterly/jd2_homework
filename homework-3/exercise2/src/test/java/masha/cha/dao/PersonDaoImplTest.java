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
        Person person = new Person(1L, 26, "Ivan", "Petrov");
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
        assertEquals("Ivan", name);
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
        rs = conn.createStatement().executeQuery("select * from " +
                "person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(27, age);
        assertEquals("Ivan", name);
        assertEquals("Petrov", surname);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void savePersonWithTrigger() {
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
        targetObject.savePersonWithTrigger(person);

        //Then
        rs = conn.createStatement().executeQuery("select * from " +
                "person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(27, age);
        assertEquals("IVAN", name);
        assertEquals("Petrov", surname);
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
    public void updatePerson() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery(
                "select count(*) from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person(1L, 12, "Ivan", "Petrov");
        targetObject.savePerson(person);
        String updateName = "Vladimir";
        person.setName(updateName);

        //When
        targetObject.updatePerson(person);

        //Then
        rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(12, age);
        assertEquals("Vladimir", name);
        assertEquals("Petrov", surname);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void updatePersonWithTrigger() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery(
                "select count(*) from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person(1L, 12, "Ivan", "Petrov");
        targetObject.savePerson(person);
        String updateSurname = "Ivanov";
        person.setSurname(updateSurname);

        //When
        targetObject.updatePersonWithTrigger(person);

        //Then
        rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(12, age);
        assertEquals("ivan", name);
        assertEquals("Ivanov", surname);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void loadById() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery(
                "select count(*) from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person(10L, 45, "Ivan", "Petrov");
        targetObject.savePerson(person);

        //When
        targetObject.loadById(person.getId());

        //Then
        rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(45, age);
        assertEquals("Ivan", name);
        assertEquals("Petrov", surname);
        conn.createStatement().executeUpdate("delete from person;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void getById() {
        //Given
        ResultSet rs = conn.createStatement().executeQuery(
                "select count(*) from person;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person = new Person(10L, 45, "Ivan", "Petrov");
        targetObject.savePerson(person);

        //When
        targetObject.getById(person.getId());

        //Then
        rs = conn.createStatement().executeQuery("select * " +
                "from person where person_id=" + person.getId() + ";");
        rs.next();
        int age = rs.getInt("age");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        assertEquals(45, age);
        assertEquals("Ivan", name);
        assertEquals("Petrov", surname);
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
        Person person = new Person(23, "Ivan", "Petrov");

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






