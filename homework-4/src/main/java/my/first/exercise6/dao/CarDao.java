package my.first.exercise6.dao;

import my.first.datasource.MysqlSessionFactory;
import my.first.exercise6.model.Car;
import org.hibernate.SessionFactory;

import java.util.UUID;

public class CarDao extends IdentityDao<UUID, Car> {

    public CarDao(SessionFactory sessionFactory) {
        super(sessionFactory, Car.class);
    }

    public CarDao() {
        super(MysqlSessionFactory.getInstance(), Car.class);
    }
}
