package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection dbConn = Util.getConnection(); Statement sqlCommand = dbConn.createStatement()) {
            sqlCommand.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT,"+
                                " name VARCHAR(40) NOT NULL, lastname VARCHAR(40), age TINYINT  NOT NULL)");
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command createUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection dbConn = Util.getConnection(); Statement sqlCommand = dbConn.createStatement()) {
            boolean res = sqlCommand.execute("DROP TABLE IF EXISTS `users`");
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command dropUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection dbConn = Util.getConnection();
             PreparedStatement sqlCommand = dbConn.prepareStatement("INSERT into users (name, lastname, age) values (?, ?, ?)")) {
            sqlCommand.setString(1,name);
            sqlCommand.setString(2,lastName);
            sqlCommand.setByte(3,age);
            sqlCommand.executeUpdate();
            System.out.printf("User с именем – %s %s добавлен в базу данных\n",name,lastName);
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command saveUser failed:");
            System.out.println(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection dbConn = Util.getConnection();
            PreparedStatement sqlCommand = dbConn.prepareStatement("DELETE from `users` where id=?")) {
            sqlCommand.setLong(1,id);
            sqlCommand.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command removeUserById failed:");
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection dbConn = Util.getConnection(); Statement sqlCommand = dbConn.createStatement()) {
            ResultSet res = sqlCommand.executeQuery("SELECT * FROM `users`");
            while (res.next()) {
                result.add(new User(res.getLong("id"),res.getString("name"),
                                    res.getString("lastname"),res.getByte("age")));
            }
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command getAllUsers failed:");
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public void cleanUsersTable() {
        try (Connection dbConn = Util.getConnection(); Statement sqlCommand = dbConn.createStatement()) {
            boolean res = sqlCommand.execute("TRUNCATE `users`");
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command cleanUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }
}
