package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.List;

public class UserServiceImpl implements UserService {
    //private final Util dbParams;
    private final UserDaoJDBCImpl userDAO;

    public UserServiceImpl () {
        /*dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                        "root","Xde%vB47&pXf");*/
        userDAO = new UserDaoJDBCImpl();
    }

    public void createUsersTable() { userDAO.createUsersTable(); }

    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) { userDAO.removeUserById(id); }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
