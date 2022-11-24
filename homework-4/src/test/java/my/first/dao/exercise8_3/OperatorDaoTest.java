package my.first.dao.exercise8_3;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise8_3.dao.OperatorDao;
import my.first.exercise8_3.model.Operator;
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

public class OperatorDaoTest extends BaseDaoTest {

    OperatorDao targetObject;

    @Before
    public void setUp() {
        targetObject = new OperatorDao(testSessionFactory);
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
                "from company_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Operator operator = Operator.builder()
                .operatorName("Alex")
                .operatorSurname("Wales")
                .salary(3400.00)
                .companyName("International Machines")
                .stuffNumber(3600)
                .build();

        //When
        targetObject.saveOrUpdate(operator);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "company_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(1, actualSize);
        conn.createStatement().executeUpdate("truncate table company_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/OperatorDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Operator operator = targetObject.findById(808);
        assertNotNull(operator);

        //Then
        assertEquals("Irvin", operator.getOperatorName());
        assertEquals("George", operator.getOperatorSurname());
        assertEquals("Flying Tiger", operator.getCompanyName());
        assertEquals(12000, operator.getStaffNumber());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/OperatorDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from company_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(808));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "company_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
