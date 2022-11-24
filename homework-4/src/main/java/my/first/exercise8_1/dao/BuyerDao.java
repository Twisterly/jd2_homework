package my.first.exercise8_1.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_1.model.Buyer;
import org.hibernate.SessionFactory;

public class BuyerDao extends DaoBase<Integer, Buyer> {

    public BuyerDao(SessionFactory sessionFactory) {
        super(sessionFactory, Buyer.class);
    }

    public BuyerDao() {
        super(MysqlSessionFactory.getInstance(), Buyer.class);
    }
}
