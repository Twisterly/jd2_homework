package masha.cha.dao;

import lombok.SneakyThrows;
import masha.cha.MysqlJdbcDataSource;
import masha.cha.pojos.Person;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseDaoTest {

    static MysqlJdbcDataSource testMysqlJdbcDataSource;

    static IDatabaseConnection iDatabaseConnection;

    static SessionFactory testSessionFactory;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("person_info_test" +
                ".jdbc" +
                ".properties");
        iDatabaseConnection =
                new MySqlConnection(testMysqlJdbcDataSource.getConnection(),
                        "person_info_test");

        StandardServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate_test.cfg.xml")
                        .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Person.class)
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }

    @Test
    public void savePerson() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void deletePerson() {
    }



}
