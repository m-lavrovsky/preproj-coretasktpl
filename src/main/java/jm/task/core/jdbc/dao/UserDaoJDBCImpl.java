package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util dbParams;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                "root","Xde%vB47&pXf");
        try (Connection conn = DriverManager.getConnection(dbParams.getUrl(), dbParams.getUser(), dbParams.getPassword())) {
            Statement sqlCommand = conn.createStatement();
            String sqlCmd="CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40) NOT NULL"+
                    ", lastname VARCHAR(40), age INT NOT NULL)";
            boolean res = sqlCommand.execute(sqlCmd);
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command createUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                "root","Xde%vB47&pXf");
        try (Connection conn = DriverManager.getConnection(dbParams.getUrl(), dbParams.getUser(), dbParams.getPassword())) {
            Statement sqlCommand = conn.createStatement();
            String sqlCmd="DROP TABLE IF EXISTS `users`";
            boolean res = sqlCommand.execute(sqlCmd);
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command dropUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                "root","Xde%vB47&pXf");
        try (Connection conn = DriverManager.getConnection(dbParams.getUrl(), dbParams.getUser(), dbParams.getPassword())) {
            Statement sqlCommand = conn.createStatement();
            String sqlCmd="Insert into users (name, lastname, age) values ('"+name+"', '"+lastName+"', "+age+")";
            boolean res = sqlCommand.execute(sqlCmd);
            System.out.printf("User с именем – %s %s добавлен в базу данных\n",name,lastName);
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command saveUser failed:");
            System.out.println(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                "root","Xde%vB47&pXf");
        try (Connection conn = DriverManager.getConnection(dbParams.getUrl(), dbParams.getUser(), dbParams.getPassword())) {
            Statement sqlCommand = conn.createStatement();
            String sqlCmd="DELETE from `users` where id='"+id+"'";
            boolean res = sqlCommand.execute(sqlCmd);
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command removeUserById failed:");
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                "root","Xde%vB47&pXf");
        try (Connection conn = DriverManager.getConnection(dbParams.getUrl(), dbParams.getUser(), dbParams.getPassword())) {
            Statement sqlCommand = conn.createStatement();
            String sqlCmd="SELECT * FROM `users`";
            ResultSet res = sqlCommand.executeQuery(sqlCmd);
            while (res.next()) {
                result.add(new User(res.getString("name"),res.getString("lastname"),res.getByte("age")));
            }
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command getAllUsers failed:");
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public void cleanUsersTable() {
        dbParams = new Util("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                "root","Xde%vB47&pXf");
        try (Connection conn = DriverManager.getConnection(dbParams.getUrl(), dbParams.getUser(), dbParams.getPassword())) {
            Statement sqlCommand = conn.createStatement();
            String sqlCmd="TRUNCATE `users`";
            boolean res = sqlCommand.execute(sqlCmd);
        }
        catch (SQLException ex) {
            System.out.println("DB connection or SQL command cleanUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }
}
