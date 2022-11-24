package my.first.exercise8_1.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_1.model.ProductInfo;
import org.hibernate.SessionFactory;

public class ProductInfoDao extends DaoBase<Integer, ProductInfo> {

    public ProductInfoDao(SessionFactory sessionFactory) {
        super(sessionFactory, ProductInfo.class);
    }

    public ProductInfoDao() {
        super(MysqlSessionFactory.getInstance(), ProductInfo.class);
    }
}
