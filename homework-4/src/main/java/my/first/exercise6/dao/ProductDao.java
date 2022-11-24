package my.first.exercise6.dao;

import my.first.datasource.MysqlSessionFactory;
import my.first.exercise6.model.Product;
import org.hibernate.SessionFactory;

import java.math.BigInteger;

public class ProductDao extends IdentityDao<BigInteger, Product> {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory, Product.class);
    }

    public ProductDao() {
        super(MysqlSessionFactory.getInstance(), Product.class);
    }
}
