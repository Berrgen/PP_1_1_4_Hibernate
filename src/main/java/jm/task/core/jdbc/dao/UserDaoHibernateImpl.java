package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    long count = 0;

    Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();
        String sqlCommand = "CREATE TABLE IF NOT EXISTS user_test (id BIGINT NOT NULL, firstname VARCHAR(255) NOT NULL, " +
                "lastname VARCHAR(255) NOT NULL, age TINYINT NOT NULL)";
        session.createSQLQuery(sqlCommand)
                .addEntity(User.class)
                .executeUpdate();

        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();

        String sqlCommand = "DROP TABLE IF EXISTS user_test";
        session.createSQLQuery(sqlCommand)
                .addEntity(User.class)
                .executeUpdate();

        transaction.commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = session.beginTransaction();

        User user = new User(name,lastName,age);
        user.setId(count++);
        session.save(user);

        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();

        String sqlCommand = "DELETE FROM user_test WHERE id = :id";
        session.createSQLQuery(sqlCommand)
                .setParameter("id",id)
                .executeUpdate();

        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        Transaction transaction = session.beginTransaction();

        String sqlCommand = "select u.* from user_test u";
        list = session.createSQLQuery(sqlCommand)
                .addEntity(User.class)
                .getResultList();

        transaction.commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();

        String sqlCommand = "TRUNCATE user_test";
        session.createSQLQuery(sqlCommand)
                .addEntity(User.class)
                .executeUpdate();

        transaction.commit();
    }

}
