package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;

    public static Connection getConnection(String hostName, String dbName, String user, String password) {
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

}
