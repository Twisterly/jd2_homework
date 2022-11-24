package my.first.exercise6.dao;

import my.first.datasource.MysqlSessionFactory;
import my.first.exercise6.model.Person;
import org.hibernate.SessionFactory;

import java.math.BigInteger;

public class PersonDao extends IdentityDao<BigInteger, Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory, Person.class);
    }

    public PersonDao() {
        super(MysqlSessionFactory.getInstance(), Person.class);
    }
}
