package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();
    //private Session session;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlCmd = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40) NOT NULL" +
                    ", lastname VARCHAR(40), age INT NOT NULL)";
            Query query = session.createSQLQuery(sqlCmd).addEntity(User.class);
            query.executeUpdate();
        }  catch (Exception e) {
            System.out.println("DB connection or SQL command createUsersTable failed:");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlCmd = "DROP TABLE IF EXISTS `users`";
            Query query = session.createSQLQuery(sqlCmd)/*.addEntity(User.class)*/;
            query.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("DB connection or SQL command dropUsersTable failed:");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlCmd = "Insert into users (name, lastname, age) values ('"+name+"', '"+lastName+"', "+age+")";
            Query query = session.createSQLQuery(sqlCmd)/*.addEntity(User.class)*/;
            query.executeUpdate();
            System.out.printf("User с именем – %s %s добавлен в базу данных\n",name,lastName);
        }
        catch (Exception e) {
            System.out.println("DB connection or SQL command saveUser failed:");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlCmd="DELETE from `users` where id='"+id+"'";
            Query query = session.createSQLQuery(sqlCmd)/*.addEntity(User.class)*/;
            query.executeUpdate();
            //System.out.println(query.getQueryString());
        }  catch (Exception ex) {
            System.out.println("DB connection or SQL command removeUserById failed:");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlCmd = "SELECT * FROM `users`";
            Query query = session.createSQLQuery(sqlCmd).addEntity(User.class);
            result = query.getResultList();
        }  catch (Exception e) {
            System.out.println("DB connection or SQL command getAllUsers failed:");
            System.out.println(e.getMessage());

        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sqlCmd = "TRUNCATE `users`";
            Query query = session.createSQLQuery(sqlCmd)/*.addEntity(User.class)*/;
            query.executeUpdate();
        }  catch (Exception ex) {
            System.out.println("DB connection or SQL command cleanUsersTable failed:");
            System.out.println(ex.getMessage());
        }
    }
}
