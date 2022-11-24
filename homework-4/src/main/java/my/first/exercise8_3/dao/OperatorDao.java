package my.first.exercise8_3.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_3.model.Operator;
import org.hibernate.SessionFactory;

public class OperatorDao extends DaoBase<Integer, Operator> {

    public OperatorDao(SessionFactory sessionFactory) {
        super(sessionFactory, Operator.class);
    }

    public OperatorDao() {
        super(MysqlSessionFactory.getInstance(), Operator.class);
    }
}
