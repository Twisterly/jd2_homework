package masha.cha;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static masha.cha.DataConfig.JDBC_PROPERTIES_FILE_NAME;

public class MysqlJdbcDataSource {

    private final String propertyFileName;

    @SneakyThrows
    public MysqlJdbcDataSource() {
        this(JDBC_PROPERTIES_FILE_NAME);
    }

    @SneakyThrows
    public MysqlJdbcDataSource(String propertyFileName) {
        this.propertyFileName = propertyFileName;
        Class.forName(DataConfig.getJdbcProperties(propertyFileName).getProperty("driver"));
    }

    @SneakyThrows
    public Connection getConnection() {
        Properties jdbcProperties = DataConfig.getJdbcProperties(propertyFileName);
        return DriverManager.getConnection(
                jdbcProperties.getProperty("url"),
                jdbcProperties.getProperty("username"),
                jdbcProperties.getProperty("password")
        );
    }
}
