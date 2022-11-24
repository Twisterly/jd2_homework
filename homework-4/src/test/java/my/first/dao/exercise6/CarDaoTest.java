package my.first.dao.exercise6;


import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise6.dao.CarDao;
import my.first.exercise6.model.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CarDaoTest extends BaseDaoTest {

    CarDao targetObject;

    @Before
    public void setUp() {
        targetObject = new CarDao(testSessionFactory);
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
                "from car_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Car car1 = Car.builder()
                .model("Audi")
                .year("2020")
                .build();
        Car car2 = Car.builder()
                .model("Bmw")
                .year("2015")
                .build();
        Car car3 = Car.builder()
                .model("Fiat")
                .year("2010")
                .build();

        //When
        targetObject.saveOrUpdate(car1);
        targetObject.saveOrUpdate(car2);
        targetObject.saveOrUpdate(car3);

        //Then
        char[] id1 = car1.getId().toString().toCharArray();
        char x1 = id1[id1.length - 1];
        int lastEl1 = x1 - '0';
        char[] id2 = car2.getId().toString().toCharArray();
        char x2 = id2[id2.length - 1];
        int lastEl2 = x2 - '0';
        char[] id3 = car3.getId().toString().toCharArray();
        char x3 = id3[id3.length - 1];
        int lastEl3 = x3 - '0';
        assertEquals(lastEl1 + 1, lastEl2);
        assertEquals(lastEl2 + 1, lastEl3);
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "car_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table car_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from car_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Car car1 = Car.builder()
                .model("Audi")
                .year("2020")
                .build();

        Car car2 = Car.builder()
                .model("Fiat")
                .year("2018")
                .build();

        targetObject.saveOrUpdate(car1);
        targetObject.saveOrUpdate(car2);
        assertNotNull(car1);
        assertNotNull(car2);

        //When
        Car car01 = targetObject.findById(car1.getId());
        Car car02 = targetObject.findById(car2.getId());

        //Then
        assertEquals("Audi", car01.getModel());
        assertEquals("2020-01-01", car01.getYear());
        assertEquals("Fiat", car02.getModel());
        assertEquals("2018-01-01", car02.getYear());
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "car_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(2, actualSize);
        conn.createStatement().executeUpdate("truncate table car_info;");
        conn.close();
    }



    @Test
    @SneakyThrows
    public void delete() {
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from car_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Car car1 = Car.builder()
                .model("Audi")
                .year("2020")
                .build();

        Car car2 = Car.builder()
                .model("Fiat")
                .year("2018")
                .build();

        targetObject.saveOrUpdate(car1);
        targetObject.saveOrUpdate(car2);
        assertNotNull(car1);
        assertNotNull(car2);

        //When
        targetObject.delete(car1);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "car_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table car_info;");
        conn.close();
    }

}





