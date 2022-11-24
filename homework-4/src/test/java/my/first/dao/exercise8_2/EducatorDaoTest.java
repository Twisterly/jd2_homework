package my.first.dao.exercise8_2;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise8_2.dao.EducatorDao;
import my.first.exercise8_2.model.Educator;
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

public class EducatorDaoTest extends BaseDaoTest {
    EducatorDao targetObject;

    @Before
    public void setUp() {
        targetObject = new EducatorDao(testSessionFactory);
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
                "from educator_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Educator educator = Educator.builder()
                .educatorName("George")
                .educatorSurname("Paul")
                .educatorExperience(7)
                .kindergartenNum(123)
                .kindergartenHead("Poppy Bean")
                .swimmingPool(true)
                .build();

        //When
        targetObject.saveOrUpdate(educator);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "educator_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table educator_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/EducatorDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Educator educator = targetObject.findById(1002);
        assertNotNull(educator);

        //Then
        assertEquals(2615, educator.getKindergartenNum());
        assertEquals(true, educator.isSwimmingPool());
        assertEquals(15, educator.getEducatorExperience());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/EducatorDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from educator_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(1002));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "educator_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}