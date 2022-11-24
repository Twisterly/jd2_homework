package my.first.dao.exercise8_2;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise8_2.dao.ChildDao;
import my.first.exercise8_2.model.Child;
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

public class ChildDaoTest extends BaseDaoTest {
    ChildDao targetObject;

    @Before
    public void setUp() {
        targetObject = new ChildDao(testSessionFactory);
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
                "from child_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Child child = Child.builder()
                .childSurname("Angela")
                .childSurname("Perkins")
                .groupNumber(12)
                .kindergartenNum(31)
                .kindergartenHead("J.P. Stewart")
                .swimmingPool(false)
                .build();

        //When
        targetObject.saveOrUpdate(child);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "child_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table child_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ChildDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Child child = targetObject.findById(88);
        assertNotNull(child);

        //Then
        assertEquals("Tommy", child.getChildName());
        assertEquals(5, child.getGroupNumber());
        assertEquals(111, child.getKindergartenNum());
        assertEquals(false, child.isSwimmingPool());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ChildDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from child_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(88));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "child_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
