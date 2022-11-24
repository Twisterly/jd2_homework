package my.first.exercise8_2.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_2.model.Child;
import org.hibernate.SessionFactory;

public class ChildDao extends DaoBase<Integer, Child> {

    public ChildDao(SessionFactory sessionFactory) {
        super(sessionFactory, Child.class);
    }

    public ChildDao() {
        super(MysqlSessionFactory.getInstance(), Child.class);
    }
}
