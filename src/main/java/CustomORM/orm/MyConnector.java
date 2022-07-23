package CustomORM.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    private Connection connection;

    public MyConnector() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", " ");

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/custom_orm", properties);
    }

    public Connection getConnection() {
        return connection;
    }
}
