package my.first.exercise8_2.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_2.model.Kindergarten;
import org.hibernate.SessionFactory;

public class KindergartenDao extends DaoBase<Integer, Kindergarten> {

    public KindergartenDao(SessionFactory sessionFactory) {
        super(sessionFactory, Kindergarten.class);
    }

    public KindergartenDao() {
        super(MysqlSessionFactory.getInstance(), Kindergarten.class);
    }
}

