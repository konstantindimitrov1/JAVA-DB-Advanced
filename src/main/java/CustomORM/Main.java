package CustomORM;

import CustomORM.entities.User;
import CustomORM.orm.EntityManager;
import CustomORM.orm.MyConnector;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MyConnector myConnector = new MyConnector();

        EntityManager<User> userEntityManager = new EntityManager<>(myConnector.getConnection());

    }
}
