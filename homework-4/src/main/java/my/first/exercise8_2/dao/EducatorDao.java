package my.first.exercise8_2.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_2.model.Educator;
import org.hibernate.SessionFactory;

public class EducatorDao extends DaoBase<Integer, Educator> {

    public EducatorDao(SessionFactory sessionFactory) {
        super(sessionFactory, Educator.class);
    }

    public EducatorDao() {
        super(MysqlSessionFactory.getInstance(), Educator.class);
    }
}
