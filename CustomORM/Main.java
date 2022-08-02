import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MyConnector myConnector = new MyConnector();

        EntityManager<User> userEntityManager = new EntityManager<>(myConnector.getConnection());

    }
}
