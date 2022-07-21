package DatabaseAccessWithJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        DBConnector connector = new DBConnector();

        int[] minionIds = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int minionId : minionIds) {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement("""
                    update minions
                    set name = lower(name), age = age + 1
                    where id = ?;""");

            preparedStatement.setInt(1, minionId);

            preparedStatement.executeUpdate();
        }

        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("select name, age from minions");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d\n", resultSet.getString("name"), resultSet.getInt("age"));
        }

        connector.getConnection().close();

    }
}
