package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John","Doe",(byte)12);
        userService.saveUser("Elon","Musk",(byte)50);
        userService.saveUser("Vasili","Pupkin",(byte)42);
        userService.saveUser("John","Smith",(byte)66);
        List<User> userList = userService.getAllUsers();
        System.out.println("Users found: "+userList.size());
        for (User i : userList) {
            System.out.println(i.toString());
        }
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
