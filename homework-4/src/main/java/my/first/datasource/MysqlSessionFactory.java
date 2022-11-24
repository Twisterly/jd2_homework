package my.first.datasource;

import my.first.exercise6.model.Car;
import my.first.exercise6.model.Project;
import my.first.exercise6.model.Person;
import my.first.exercise6.model.Product;
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
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;

public class MysqlSessionFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        return getInstance(DataConfig.JDBC_PROPERTIES_FILE_NAME,
                DataConfig.HIBERNATE_PROPERTIES_FILE_NAME);
    }

    public static SessionFactory getInstance(String jdbcPropertiesFileName,
                                             String hibernatePropertiesFileName) {
        if (sessionFactory != null) {
            return sessionFactory;
        }

        StandardServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder()
                        .applySettings(buildSettings(jdbcPropertiesFileName,
                                hibernatePropertiesFileName))
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

        sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }

    private static Map<String, String> buildSettings(String jdbcPropertiesFileName,
                                                     String hibernatePropertiesFileName) {
        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("driver"));
        settings.put("hibernate.connection.url",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("url"));
        settings.put("hibernate.connection.username",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("username"));
        settings.put("hibernate.connection.password",
                DataConfig.getJdbcProperties(jdbcPropertiesFileName).getProperty("password"));

        settings.put("dialect",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("dialect"));
        settings.put("hibernate.current_session_context_class",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.current_session_context_class"));
        settings.put("hibernate.show_sql",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.show_sql"));
        settings.put("hibernate.format_sql",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.format_sql"));
        settings.put("hibernate.hbm2ddl.auto",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.hbm2ddl.auto"));
        settings.put("hibernate.cache.provider_class",
                DataConfig.getHibernateProperties(hibernatePropertiesFileName).getProperty("hibernate.cache.provider_class"));
        return settings;
    }
}
