package my.first.dao;

import lombok.SneakyThrows;
import my.first.datasource.MysqlJdbcDataSource;
import my.first.exercise6.model.Car;
import my.first.exercise6.model.Person;
import my.first.exercise6.model.Product;
import my.first.exercise6.model.Project;
import my.first.exercise7.model.Address;
import my.first.exercise7.model.Delivery;
import my.first.exercise7.model.Driver;
import my.first.exercise7.model.Lorry;
import my.first.exercise8_1.model.Buyer;
import my.first.exercise8_1.model.ProductInfo;
import my.first.exercise8_1.model.Shop;
import my.first.exercise8_2.model.Child;
import my.first.exercise8_2.model.Educator;
import my.first.exercise8_2.model.Kindergarten;
import my.first.exercise8_3.model.Company;
import my.first.exercise8_3.model.Equipment;
import my.first.exercise8_3.model.Operator;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.BeforeClass;

public class BaseDaoTest {

    public static MysqlJdbcDataSource testMysqlJdbcDataSource;
    // DBUnit connection
    public static IDatabaseConnection iDatabaseConnection;
    //Hibernate session factory
    public static SessionFactory testSessionFactory;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("homework_4_DB_test" +
                ".jdbc.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "homework_4_DB_test");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate_test.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Delivery.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Lorry.class)
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(Buyer.class)
                .addAnnotatedClass(ProductInfo.class)
                .addAnnotatedClass(Shop.class)
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Educator.class)
                .addAnnotatedClass(Kindergarten.class)
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Operator.class)
                .addAnnotatedClass(Equipment.class)
                .getMetadataBuilder()
                .build();
        testSessionFactory = metadata.getSessionFactoryBuilder()
                .build();
    }

}


