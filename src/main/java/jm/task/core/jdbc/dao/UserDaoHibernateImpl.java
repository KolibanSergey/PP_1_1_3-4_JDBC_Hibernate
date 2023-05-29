package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.hibernateConnection();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User" +
                    "(id BIGINT not null auto_increment," +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age INT, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction!= null) transaction.rollback();
        }


    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS User").executeUpdate();
           transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            if (transaction!= null) transaction.rollback();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }

    }

    @Override

    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();){
            transaction = session.getTransaction();
            transaction.begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<User> delete = builder.createCriteriaDelete(User.class);
            Root<User> geekRoot = delete.from(User.class);
            delete.where(builder.equal(geekRoot.get("id"), id));
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
            query.from(User.class);
            List<User> users = session.createQuery(query).getResultList();
            transaction.commit();
            return users;
            } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction!= null) transaction.rollback();
            return null;
        }

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction!= null) transaction.rollback();
        }
    }
}
