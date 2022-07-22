package DatabaseAccessWithJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        List<String> input = Arrays.stream(scanner.nextLine().split("\\s+")).skip(1).toList();

        String minionName = input.get(0);
        int minionAge = Integer.parseInt(input.get(1));
        String minionTown = input.get(2);
        String villainName = scanner.nextLine().split("\\s+")[1];

        DBConnector dbConnector = new DBConnector();

        PreparedStatement prepareTown = dbConnector.getConnection().prepareStatement("select * from towns as t\n" +
                "where t.name like ?;");
        prepareTown.setString(1, minionTown);

        PreparedStatement prepareVillain = dbConnector.getConnection().prepareStatement("select * from villains\n" +
                "where name like ?;");
        prepareVillain.setString(1, villainName);

        ResultSet townResult = prepareTown.executeQuery();

        if (!townResult.next()) {
            PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement("insert into towns (`name`)\n" +
                    "values (?);");

            preparedStatement.setString(1, minionTown);

            preparedStatement.executeUpdate();

            System.out.printf("Town %s was added to the database.\n", minionTown);
        }

        ResultSet villainResult = prepareVillain.executeQuery();

        if (!villainResult.next()) {
            PreparedStatement preparedStatement = dbConnector.getConnection().prepareStatement("insert into villains (`name`, evilness_factor)\n" +
                    "values (?, 'evil');");

            preparedStatement.setString(1, villainName);

            preparedStatement.executeUpdate();

            System.out.printf("Villain %s was added to the database.\n", villainName);
        }

        PreparedStatement getMinionTownId = dbConnector.getConnection().prepareStatement("select id from towns\n" +
                "where `name` like ?;");
        getMinionTownId.setString(1, minionTown);
        ResultSet minionTownIdResult = getMinionTownId.executeQuery();
        minionTownIdResult.next();
        int minionTownId = minionTownIdResult.getInt("id");

        PreparedStatement insertMinion = dbConnector.getConnection().prepareStatement("insert into minions (`name`, age, town_id) \n" +
                "values (?, ?, ?);");
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, minionTownId);
        insertMinion.executeUpdate();

        PreparedStatement getMinionId = dbConnector.getConnection().prepareStatement("select id from minions\n" +
                "where `name` like ?;");
        getMinionId.setString(1, minionName);
        ResultSet minionIdResult = getMinionId.executeQuery();
        minionIdResult.next();
        int minionId = minionIdResult.getInt("id");

        PreparedStatement getVillainId = dbConnector.getConnection().prepareStatement("select id from villains\n" +
                "where `name` like '%s';");
        ResultSet villainIdResult = getVillainId.executeQuery();
        villainIdResult.next();
        int villainId = villainIdResult.getInt("id");

        PreparedStatement attachMinionToVillain = dbConnector.getConnection().prepareStatement("insert into minions_villains (`minion_id`, villain_id) \n" +
                "values (?, ?);");
        attachMinionToVillain.setInt(1, minionId);
        attachMinionToVillain.setInt(2, villainId);
        attachMinionToVillain.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.", minionName, villainName);

        dbConnector.getConnection().close();
    }
}
