package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class  Util {
    private final String url;
    private final String user;
    private final String password;
    private Connection dbConnection;

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    static {
        try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            // Hibernate settings equivalent to hibernate.cfg.xml's properties
            Map<String, String> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/task-113?useSSL=false&serverTimezone=UTC");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "Xde%vB47&pXf");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            // Create registry
            registry = registryBuilder.applySettings(settings).build();

            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);

            // Create Metadata
            Metadata metadata = sources.addAnnotatedClass(User.class).getMetadataBuilder().build();

            // Create SessionFactory
            sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(registry);
                /*e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }*/
            }
        //return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {return sessionFactory;}

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
