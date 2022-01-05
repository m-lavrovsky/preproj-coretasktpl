package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  Util {
    private final String url;
    private final String user;
    private final String password;
    private Connection dbConnection;

    // реализуйте настройку соеденения с БД
    public Util (String url, String user, String password) {
        this.url = url;
        this.password = password;
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() { return password; }

    /*public void connect() throws SQLException {
        try {
            System.out.println("Establishing DB connection...");
            dbConnection = DriverManager.getConnection(url, username, password);
            System.out.println("DB connection successful");
        }
        catch(SQLException ex) {
            System.out.println("DB connection failed:");
            System.out.println(ex);
        }
    }

    public void close() {
        dbConnection.close();
    }*/
}
