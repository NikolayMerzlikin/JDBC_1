package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;
    //private static Session session;
    private static SessionFactory sessionFactory;

    public static Connection getConnectionJDBC(String hostName, String dbName, String user, String password) {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        if (connection == null) {
            try {
                //Class.forName("org.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(connectionURL, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static void ConfigHibernate() {
        if (sessionFactory == null) {
            Configuration config = new Configuration().addAnnotatedClass(User.class);
            sessionFactory = config.buildSessionFactory();
        }
    }

    public static Session getSession() {
        ConfigHibernate();
        return sessionFactory.openSession();
    }
}
