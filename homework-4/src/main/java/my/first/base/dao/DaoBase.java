package my.first.base.dao;

import my.first.base.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public abstract class DaoBase<K extends Serializable, E extends BaseEntity<K>>
        implements Dao<K, E>{

    private final SessionFactory sessionFactory;
    private final Class<E> clazz;
    private final Logger log;

    protected DaoBase(SessionFactory sessionFactory, Class<E> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
        this.log = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void delete(E entity) {
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            sess.delete(entity);
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error: " + e);
        }
    }

    @Override
    public void saveOrUpdate(E entity) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.saveOrUpdate(entity);
            log.info(entity.toString());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.error("Error: " + e);
        }
    }

    @Override
    public E findById(K id) {
        E entity = null;
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            entity = sess.get(clazz, id);
            System.out.println("ID: " + entity.getId());
            log.info(entity.toString());
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error: " + e);
        }
        return entity;
    }
}
