package masha.cha;

import lombok.SneakyThrows;

import java.util.Properties;

public class DataConfig {

    public static final String JDBC_PROPERTIES_FILE_NAME = "jpadb.jdbc.properties";
    public static final String HIBERNATE_PROPERTIES_FILE_NAME = "hibernate.properties";

    private static Properties jdbcProperties;
    private static Properties hibernateProperties;

    @SneakyThrows
    public static Properties getJdbcProperties(String propertyFileName) {
        if (jdbcProperties == null) {
            jdbcProperties = new Properties();
            jdbcProperties.load(MysqlJdbcDataSource.class
                    .getClassLoader()
                    .getResourceAsStream(propertyFileName));
        }
        return jdbcProperties;
    }

    @SneakyThrows
    public static Properties getHibernateProperties(String propertyFileName) {
        if (hibernateProperties == null) {
            hibernateProperties = new Properties();
            hibernateProperties.load(MysqlJdbcDataSource.class
                    .getClassLoader()
                    .getResourceAsStream(propertyFileName));
        }
        return hibernateProperties;
    }

}
