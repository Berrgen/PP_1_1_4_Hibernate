package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Viktor","Makarov", (byte) 25);
        user.saveUser("Ivan","Kovach", (byte) 35);
        user.saveUser("Petr","Robins", (byte) 21);
        user.saveUser("Nikolay","Evans", (byte) 28);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
