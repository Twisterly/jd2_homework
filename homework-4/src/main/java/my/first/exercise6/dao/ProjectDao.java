package my.first.exercise6.dao;

import my.first.datasource.MysqlSessionFactory;
import my.first.exercise6.model.Project;
import org.hibernate.SessionFactory;

public class ProjectDao extends IdentityDao<Long, Project> {

    public ProjectDao(SessionFactory sessionFactory) {
        super(sessionFactory, Project.class);
    }

    public ProjectDao() {
        super(MysqlSessionFactory.getInstance(), Project.class);
    }
}
