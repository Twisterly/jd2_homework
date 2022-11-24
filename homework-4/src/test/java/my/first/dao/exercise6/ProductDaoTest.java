package my.first.dao.exercise6;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise6.dao.ProductDao;
import my.first.exercise6.model.Product;
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

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProductDaoTest extends BaseDaoTest {

    ProductDao targetObject;

    @Before
    public void setUp() {
        targetObject = new ProductDao(testSessionFactory);
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
                "from product_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Product product1 = Product.builder()
                .name("Chocolate")
                .price(12.50)
                .build();
        Product product2 = Product.builder()
                .name("Water")
                .price(1.50)
                .build();
        Product product3 = Product.builder()
                .name("Cheese")
                .price(25.90)
                .build();

        //When
        targetObject.saveOrUpdate(product1);
        targetObject.saveOrUpdate(product2);
        targetObject.saveOrUpdate(product3);

        //Then
        assertEquals(product1.getId().add(BigInteger.ONE), product2.getId());
        assertEquals(product2.getId().add(BigInteger.ONE), product3.getId());
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "product_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table product_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProductDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Product product = targetObject.findById(new BigInteger("15"));
        assertNotNull(product);

        //Then
        assertEquals("Biscuit", product.getName());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProductDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from product_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(new BigInteger("15")));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "product_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
