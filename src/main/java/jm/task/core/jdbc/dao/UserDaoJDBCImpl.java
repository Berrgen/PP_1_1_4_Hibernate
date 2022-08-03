package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private int id = 0;
    private Connection connection = Util.getConnection();

//    Класс dao должен иметь конструктор пустой/по умолчанию
    public UserDaoJDBCImpl() {

    }

//    Создание таблицы для User(ов)
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS user_test (id INT, firstname LONGTEXT, lastname VARCHAR(20), age INT)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("dataBase is created...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS user_test";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("dataBase is delete...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT user_test (id, firstname, lastname, age) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setInt(1, getId());
            statement.setString(2, name);
            statement.setString(3, lastName);
            statement.setByte(4, age);
            statement.execute();
            System.out.println("User " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM user_test WHERE id";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("User is delete...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    Получение всех User(ов) из таблицы
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT u.* FROM user_test u";
            ResultSet result= statement.executeQuery(SQL);
            while (result.next()) {
                User user = new User(result.getString(2), result.getString(3), result.getByte(4));
                users.add(user);
            }
            System.out.println(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

//    Очистка содержания таблицы
    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE user_test";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
            System.out.println("Users is delete...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getId() {
        return ++id;
    }

}
