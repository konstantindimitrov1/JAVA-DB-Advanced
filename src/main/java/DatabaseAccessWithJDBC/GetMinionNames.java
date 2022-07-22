package DatabaseAccessWithJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        DBConnector dbConnector = new DBConnector();

        PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement("""
                select m.name, m.age, v.name from minions as m
                join minions_villains as mv
                on m.id = mv.minion_id
                join villains as v
                on v.id = mv.villain_id
                where v.id = ?
                group by m.id;""");

        Scanner scanner = new Scanner(System.in);

        int villainID = Integer.parseInt(scanner.nextLine());

        preparedStatement.setInt(1, villainID);

        ResultSet resultSet = preparedStatement.executeQuery();

        int number = 1;

        while (resultSet.next()) {
            if (number == 1) {
                System.out.println("Villain: " + resultSet.getString("v.name"));
            }

            System.out.printf("%d. %s %d\n", number, resultSet.getString("name"), resultSet.getInt("age"));
            number++;
        }

        if (number == 1) {
            System.out.printf("No villain with ID %d exists in the database.", villainID);
        }

        dbConnector.getConnection().close();
    }
}
