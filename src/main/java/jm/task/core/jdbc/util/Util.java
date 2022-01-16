package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  Util {
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC",
                    "root", "Xde%vB47&pXf");
        }
        catch(SQLException ex) {
            System.out.println("Error occured while trying to get DB connection:");
            System.out.println(ex);
        }
        return connection;
    }

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        }
        catch(SQLException ex) {
            System.out.println("Error occured while trying to register driver:");
            System.out.println(ex);
        }
    }
}
