package masha.cha.dao;

import masha.cha.MysqlSessionFactory;
import masha.cha.pojos.Person;
import masha.cha.util.TriggerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.*;


public class PersonDaoImpl implements PersonDao {

    private final SessionFactory sessionFactory;
    TriggerManager triggerManager = new TriggerManager();
    private final Logger log = LoggerFactory.getLogger(PersonDaoImpl.class);


    public PersonDaoImpl() {
        sessionFactory = MysqlSessionFactory.getInstance();
    }

    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person findById(Long id) {
        return sessionFactory.openSession().get(Person.class, id);
    }

    @Override
    public void savePerson(Person person) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.save(person);
            sess.get(Person.class, person.getId());
            tx.commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
            if (tx != null) tx.rollback();
            throw e;
        }


    }

    public Person savePersonWithTrigger(Person person) {
        Transaction tx = null;
        triggerManager.createSaveTrigger();
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.save(person);
            Long id = person.getId();
            sess.flush();
            if (sess.contains(person)) {
                sess.evict(person);
            }
            person = sess.get(Person.class, id);
            tx.commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
            if (tx != null) tx.rollback();
            throw e;
        }
        triggerManager.deleteSaveTrigger();
        return person;
    }




    @Override
    public Person deletePerson(long id) {
        Person personForDelete;
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            personForDelete = sess.get(Person.class, id);
            sess.delete(personForDelete);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.error("Error in Dao: " + e);
            throw e;
        }
        return personForDelete;
    }

    public void updatePerson(Person person) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.update(person);
            tx.commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Person updatePersonWithTrigger(Person person) {
        Transaction tx = null;
        triggerManager.createUpdateTrigger();
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.update(person);
            Long id = person.getId();
            sess.flush();
            if (sess.contains(person)) {
                sess.evict(person);
            }
            person = sess.get(Person.class, id);
            tx.commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
            if (tx != null) tx.rollback();
            throw e;
        }
        triggerManager.deleteUpdateTrigger();
        return person;
    }


    public Person loadById(Long id) {
        Person person = null;
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            person = sess.load(Person.class, id);
            System.out.println("Person ID: " + person.getId());
            log.info(person.toString());
            person.getName();
            sess.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
        }
        return person;
    }


    public Person getById(Long id) {
        Person person = null;
        try {
            Session sess = sessionFactory.openSession();
            Transaction tx = sess.beginTransaction();
            person = sess.get(Person.class, id);
            tx.commit();
            sess.close();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
        }
        return person;
    }


    //@Overload
    public void deletePerson(Person person) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            Person personForDelete = sess.get(Person.class, person.getId());
            sess.delete(personForDelete);
            tx.commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void saveAndDeletePerson(Person person) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.save(person);
            sess.delete(person);
            tx.commit();
        } catch (Exception e) {
            log.error("Error in Dao: " + e);
            if (tx != null) tx.rollback();
            throw e;
        }
    }


}







