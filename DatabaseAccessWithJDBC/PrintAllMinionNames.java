package DatabaseAccessWithJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {

        DBConnector connector = new DBConnector();

        PreparedStatement allMinionNames = connector.getConnection().prepareStatement("select name from minions;");

        ResultSet minionNamesResult = allMinionNames.executeQuery();

        Deque<String> mNames = new ArrayDeque<>();

        while (minionNamesResult.next()) {
            mNames.add(minionNamesResult.getString("name"));
        }

        StringBuilder sb = new StringBuilder();

        while (!mNames.isEmpty()) {
            sb.append(mNames.poll()).append(System.lineSeparator());

            if (mNames.isEmpty()) {
                break;
            }

            sb.append(mNames.pollLast()).append(System.lineSeparator());
        }

        System.out.println(sb);

    }
}
