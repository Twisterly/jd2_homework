package my.first.dao.exercise6;

import lombok.SneakyThrows;
import my.first.dao.BaseDaoTest;
import my.first.exercise6.dao.ProjectDao;
import my.first.exercise6.model.Project;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class ProjectDaoTest extends BaseDaoTest {

    ProjectDao targetObject;

    @Before
    public void setUp() {
        targetObject = new ProjectDao(testSessionFactory);
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
                "from project_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
        Project project1 = Project.builder()
                .name("course work")
                .build();
        Project project2 = Project.builder()
                .name("diplom")
                .build();
        Project project3 = Project.builder()
                .name("test")
                .build();

        //When
        targetObject.saveOrUpdate(project1);
        targetObject.saveOrUpdate(project2);
        targetObject.saveOrUpdate(project3);

        //Then
        assertTrue(project1.getId() + 1 == project2.getId());
        assertTrue(project2.getId() + 1 == project3.getId());
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "project_info;");
        rs.next();
        int actualSize = rs.getInt(1);
        assertEquals(3, actualSize);
        conn.createStatement().executeUpdate("truncate table project_info;");
        conn.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProjectDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        //When
        Project project = targetObject.findById(100L);
        assertNotNull(project);

        //Then
        assertEquals("Final test", project.getName());
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void delete() {
        //Given
        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(new File("src/test/resources/ProjectDaoTest.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);
        Connection conn = testMysqlJdbcDataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("select count(*) " +
                "from project_info;");
        rs.next();
        int initialSize = rs.getInt(1);
        assertEquals(1, initialSize);

        //When
        targetObject.delete(targetObject.findById(100L));

        //Then
        rs = conn.createStatement().executeQuery("select count(*) from " +
                "project_info;");
        rs.next();
        initialSize = rs.getInt(1);
        assertEquals(0, initialSize);
    }
}
