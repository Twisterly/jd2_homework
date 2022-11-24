package my.first.exercise7.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise7.model.Delivery;
import org.hibernate.SessionFactory;

public class DeliveryDao extends DaoBase<Integer, Delivery> {

    public DeliveryDao(SessionFactory sessionFactory) {
        super(sessionFactory, Delivery.class);
    }

    public DeliveryDao() {
        super(MysqlSessionFactory.getInstance(), Delivery.class);
    }
}
