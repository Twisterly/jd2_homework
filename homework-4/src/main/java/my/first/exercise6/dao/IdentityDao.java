package my.first.exercise6.dao;

import my.first.base.dao.DaoBase;
import my.first.base.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;

public class IdentityDao<K extends Serializable, E extends BaseEntity<K>>
        extends DaoBase<K, E> implements IPrint<K, E> {

    private final SessionFactory sessionFactory;
    private final Class<E> clazz;

    public IdentityDao(SessionFactory sessionFactory, Class<E> clazz) {
        super(sessionFactory, clazz);
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public void saveAndPrintIdentity(E entity) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.saveOrUpdate(entity);
            sess.flush();
            System.out.println("Entity class is: " + clazz.getName());
            System.out.println("Identity is: " + sess.getIdentifier(entity));
            System.out.println("Entity: " + entity.toString());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}

