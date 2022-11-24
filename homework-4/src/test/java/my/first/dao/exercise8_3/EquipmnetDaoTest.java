package my.first.dao.exercise8_3;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise8_3.dao.EquipmentDao;
import my.first.exercise8_3.model.Equipment;
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

public class EquipmnetDaoTest extends BaseDaoTest {

    EquipmentDao targetObject;

    @Before
    public void setUp() throws Exception {
        targetObject = new EquipmentDao(testSessionFactory);
    }

    @After
    public void tearDown() throws Exception {
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
        Equipment equipment = Equipment.builder()
                .model("Shantui D80")
                .companyName("Worldwide rent")
                .stuffNumber(190)
                .build();

        //When
        targetObject.saveOrUpdate(equipment);

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
                .build(new File("src/test/resources/EquipmentDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Equipment equipment = targetObject.findById(500);
        assertNotNull(equipment);

        //Then
        assertEquals("Builder", equipment.getCompanyName());
        assertEquals("Doosan brew-908", equipment.getModel());
        assertEquals(100, equipment.getStaffNumber());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/EquipmentDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from company_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(500));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "company_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
