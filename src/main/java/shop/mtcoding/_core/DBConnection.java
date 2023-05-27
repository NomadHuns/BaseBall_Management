package shop.mtcoding._core;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private DBConnection() {}

    public static Connection getConnection() throws Exception{
        Connection connection = null;
        if (connection == null) {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/baseball", "ssar", "1234");
        }
        return connection;
    }
}
