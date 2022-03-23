package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.util.List;

import static java.lang.String.*;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov", (byte) 10);
        System.out.printf("User с именем – %s добавлен в базу данных%n","Ivan");
        userService.saveUser("Petr","Petrov", (byte) 20);
        System.out.printf("User с именем – %s добавлен в базу данных%n","Petr");
        userService.saveUser("Vasya","Pupkin", (byte) 17);
        System.out.printf("User с именем – %s добавлен в базу данных%n","Vasya");
        userService.saveUser("John","Doe", (byte) 40);
        System.out.printf("User с именем – %s добавлен в базу данных%n","John");
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
