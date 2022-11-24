package my.first.dao.exercise8_1;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise8_1.dao.BuyerDao;
import my.first.exercise8_1.model.Buyer;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuyerDaoTest extends BaseDaoTest {
    BuyerDao targetObject;

    @Before
    public void setUp() {
        targetObject = new BuyerDao(testSessionFactory);
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
                "from buyer_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "productJ_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Buyer buyer = Buyer.builder()
                .buyerName("Julia")
                .buyerSurname("Philips")
                .buyerAge(29)
                .productName("Coffee")
                .price(31.90)
                .build();

        //When
        targetObject.saveOrUpdate(buyer);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "buyer_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "productJ_info;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        conn.createStatement().executeUpdate("truncate table buyer_info;");
        conn.createStatement().executeUpdate("truncate table productJ_info;");
        conn.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/BuyerDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Buyer buyer = targetObject.findById(405);
        assertNotNull(buyer);

        //Then
        assertEquals("John", buyer.getBuyerName());
        assertEquals("Avocado", buyer.getProductName());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/BuyerDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from buyer_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "productJ_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(405));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "buyer_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "productJ_info;");
        rs.next();
        actualSize = rs.getInt(1);
        assertEquals(0, actualSize);
    }
}
