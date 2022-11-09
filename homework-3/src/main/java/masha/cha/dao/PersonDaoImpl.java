package masha.cha.dao;

import masha.cha.MysqlSessionFactory;
import masha.cha.pojos.Person;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class PersonDaoImpl implements PersonDao {

    private final SessionFactory sessionFactory;


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
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void deletePerson(long id) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            Person personForDelete = sess.get(Person.class, id);
            sess.delete(personForDelete);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public String loadById(Long id) {
        Person person = null;
        try (Session sess = sessionFactory.openSession()) {
            sess.beginTransaction();
            person = sess.load(Person.class, id);
            System.out.println("Person ID: " + person.getId());
            System.out.println("Person Name: " + person.getName());
            sess.getTransaction().commit();
        } catch (ObjectNotFoundException e) {
            System.out.println("ObjectNotFoundException:" + e.getMessage());
            return "There is no person with such ID";
        }
        return person.toString();
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
            e.printStackTrace();
        }
        return person;
    }

    public boolean toUpperCasePersonName(long id) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            Person personForUpdate = sess.get(Person.class, id);
            personForUpdate.setName(personForUpdate.getName().toUpperCase());
            sess.update(personForUpdate);
            System.out.println(sess.get(Person.class, id));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        return true;
    }

    public boolean toLowerCasePersonSurname(long id) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            Person personForUpdate = sess.get(Person.class, id);
            personForUpdate.setName(personForUpdate.getSurname().toLowerCase());
            sess.update(personForUpdate);
            System.out.println(sess.get(Person.class, id));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
        return true;
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
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
