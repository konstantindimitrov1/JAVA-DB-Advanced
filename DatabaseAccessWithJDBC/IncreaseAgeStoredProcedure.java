package DatabaseAccessWithJDBC;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        DBConnector connector = new DBConnector();

        CallableStatement callableStatement = connector.getConnection().prepareCall("call usp_get_older(?)");

        int minionId = Integer.parseInt(scanner.nextLine());
        callableStatement.setInt(1, minionId);

        callableStatement.execute();

        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("select name, age from minions where id = ?;");

        preparedStatement.setInt(1, minionId);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));
    }
}
