package my.first.dao.exercise7;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise7.dao.DeliveryDao;
import my.first.exercise7.model.Address;
import my.first.exercise7.model.Delivery;
import my.first.exercise7.model.Driver;
import my.first.exercise7.model.Lorry;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeliveryDaoTest extends BaseDaoTest {

    DeliveryDao targetObject;

    @Before
    public void setUp()  {
        targetObject = new DeliveryDao(testSessionFactory);
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
                "from delivery_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Delivery delivery = Delivery.builder()
                .date(Date.valueOf("2022-12-09"))
                .address(Address.builder()
                        .city("Minsk")
                        .street("Pervomayskaya")
                        .houseNumber(21)
                        .postcode(220080)
                        .build())
                .lorry(Lorry.builder()
                        .model("DAF")
                        .number("A-1286-HFG")
                        .driver(Driver.builder()
                                .name("Igor")
                                .surname("Igorev")
                                .build())
                        .build())
                .build();

        //When
        targetObject.saveOrUpdate(delivery);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "delivery_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table delivery_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/DeliveryDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Delivery delivery = targetObject.findById(100);
        assertNotNull(delivery);

        //Then
        assertEquals(Date.valueOf("2022-11-29"), delivery.getDate());
        assertEquals("Minsk", delivery.getAddress().getCity());
        assertEquals("Surganova", delivery.getAddress().getStreet());
        assertEquals(15, delivery.getAddress().getHouseNumber());
        assertEquals(220010, delivery.getAddress().getPostcode());
        assertEquals("RENAULT", delivery.getLorry().getModel());
        assertEquals("A-6429-GFE", delivery.getLorry().getNumber());
        assertEquals("Ilya", delivery.getLorry().getDriver().getName());
        assertEquals("Kotikov", delivery.getLorry().getDriver().getSurname());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/DeliveryDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) from delivery_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(100));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "delivery_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}