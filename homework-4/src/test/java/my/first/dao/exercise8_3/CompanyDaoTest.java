package my.first.dao.exercise8_3;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise8_3.dao.CompanyDao;
import my.first.exercise8_3.model.Company;
import my.first.exercise8_3.model.Equipment;
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

public class CompanyDaoTest extends BaseDaoTest {

    CompanyDao targetObject;

    @Before
    public void setUp() {
        targetObject = new CompanyDao(testSessionFactory);
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
        Company company1 = Operator.builder()
                .operatorName("Jeffrey")
                .operatorSurname("Adams")
                .salary(2500.00)
                .companyName("Adamant Co")
                .stuffNumber(1500)
                .build();
        Company company2 = Equipment.builder()
                .model("Caterpillar C300")
                .companyName("Diggers")
                .stuffNumber(350)
                .build();

        //When
        targetObject.saveOrUpdate(company1);
        targetObject.saveOrUpdate(company2);

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "company_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(2, actualSize);
        conn.createStatement().executeUpdate("truncate table company_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/CompanyDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Company company = targetObject.findById(99);
        assertNotNull(company);

        //Then
        assertEquals("Komatsu", company.getCompanyName());
        assertEquals(2500, company.getStaffNumber());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/CompanyDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from company_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(99));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "company_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
