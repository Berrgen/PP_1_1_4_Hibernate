package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sqlCommand = "CREATE TABLE IF NOT EXISTS user_test (id BIGINT NOT NULL AUTO_INCREMENT, firstname VARCHAR(255) NOT NULL, " +
                    "lastname VARCHAR(255) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";
            session.createSQLQuery(sqlCommand)
                    .addEntity(User.class)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sqlCommand = "DROP TABLE IF EXISTS user_test";
            session.createSQLQuery(sqlCommand)
                    .addEntity(User.class)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sqlCommand = "DELETE FROM user_test WHERE id = :id";
            session.createSQLQuery(sqlCommand)
                    .setParameter("id", id)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        Session session = Util.getSessionFactory().openSession();

        String sqlCommand = "select * from user_test";
        list = session.createSQLQuery(sqlCommand)
                .addEntity(User.class)
                .getResultList();

        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sqlCommand = "TRUNCATE user_test";
            session.createSQLQuery(sqlCommand)
                    .addEntity(User.class)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
