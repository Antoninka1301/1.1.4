package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                String myTableName2 = "CREATE TABLE IF NOT EXISTS users " +
                        "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                        "age TINYINT NOT NULL)";
                session.createSQLQuery(myTableName2).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception p) {
                session.getTransaction().rollback();

            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();

                session.getTransaction().commit();
            } catch (Exception p) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
            } catch (Exception p) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.remove(user);
                session.getTransaction().commit();
            } catch (Exception p) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            userList = session.createQuery("select i from User i", User.class).getResultList();
        } catch (Exception e) {

        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.createQuery("delete from User").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception p) {
                session.getTransaction().rollback();
            }
        }
    }
}
