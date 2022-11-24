package my.first.dao.exercise6;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise6.dao.PersonDao;
import my.first.exercise6.model.Person;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonDaoTest extends BaseDaoTest {

    PersonDao targetObject;

    @Before
    public void setUp() {
        targetObject = new PersonDao(testSessionFactory);
    }

    @After
    public void tearDown() {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void saveOrUpdate() {
        //Given
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from person_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Person person1 = Person.builder()
                .name("Ivan")
                .surname("Ivanov")
                .age(25)
                .build();
        Person person2 = Person.builder()
                .name("Julia")
                .surname("Petrova")
                .age(19)
                .build();
        Person person3 = Person.builder()
                .name("Petr")
                .surname("Sidorov")
                .age(40)
                .build();

        //When
        targetObject.saveOrUpdate(person1);
        targetObject.saveOrUpdate(person2);
        targetObject.saveOrUpdate(person3);

        //Then
        assertEquals(person1.getId().add(BigInteger.ONE), person2.getId());
        assertEquals(person2.getId().add(BigInteger.ONE), person3.getId());
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "person_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table person_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PersonDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Person person = targetObject.findById(new BigInteger("500"));
        assertNotNull(person);

        //Then
        assertEquals("John", person.getName());
        assertEquals("Willson", person.getSurname());
        assertEquals(39, person.getAge());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/PersonDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from person_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(new BigInteger("500")));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "person_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}

