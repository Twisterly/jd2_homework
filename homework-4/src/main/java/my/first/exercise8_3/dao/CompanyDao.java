package my.first.exercise8_3.dao;

import my.first.base.dao.DaoBase;
import my.first.datasource.MysqlSessionFactory;
import my.first.exercise8_3.model.Company;
import org.hibernate.SessionFactory;

public class CompanyDao extends DaoBase<Integer, Company> {

    public CompanyDao(SessionFactory sessionFactory) {
        super(sessionFactory, Company.class);
    }

    public CompanyDao() {
        super(MysqlSessionFactory.getInstance(), Company.class);
    }
}
