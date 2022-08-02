import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNameCasing {
    public static void main(String[] args) throws SQLException {

        DBConnector connector = new DBConnector();

        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("select * from towns\n" +
                "where country like ?;");

        Scanner scanner = new Scanner(System.in);

        String country = scanner.nextLine();

        preparedStatement.setString(1, country);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> towns = new ArrayList<>();

        if (!resultSet.next()) {
            System.out.println("No town names were affected.");
            return;
        } else {
            towns.add(resultSet.getString("name").toUpperCase());
            while (resultSet.next()) {
                towns.add(resultSet.getString("name").toUpperCase());
            }
        }

        for (String town : towns) {
            PreparedStatement updateTown = connector.getConnection().prepareStatement("""
                    update towns
                    set name = upper(name)
                    where name like ?;""");

            updateTown.setString(1, town);

            updateTown.executeUpdate();
        }

        System.out.printf("%d town names were affected.\n%s", towns.size(), towns);

        connector.getConnection().close();

    }
}
