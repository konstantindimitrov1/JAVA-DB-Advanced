package DatabaseAccessWithJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private Connection connection;

    public DBConnector () throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
