package repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/appstore";
            String user = "postgres";
            String password = "postgres";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
