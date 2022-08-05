package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Viktor","Makarov", (byte) 25);
        userService.saveUser("Ivan","Kovach", (byte) 35);
        userService.saveUser("Petr","Robins", (byte) 21);
        userService.saveUser("Nikolay","Evans", (byte) 28);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
