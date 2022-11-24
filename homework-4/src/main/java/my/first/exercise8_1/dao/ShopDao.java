package my.first.exercise8_1.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_1.model.Shop;
import org.hibernate.SessionFactory;

public class ShopDao extends DaoBase<Integer, Shop> {

    public ShopDao(SessionFactory sessionFactory) {
        super(sessionFactory, Shop.class);
    }

    public ShopDao() {
        super(MysqlSessionFactory.getInstance(), Shop.class);
    }
}

