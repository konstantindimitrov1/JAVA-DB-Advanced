package DatabaseAccessWithJDBC;

import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "kosiokosio9");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement preparedStatement = connection.prepareStatement("""
                select v.name, count(distinct mv.minion_id) as m_count from villains as v
                left join minions_villains as mv
                on v.id = mv.villain_id
                group by v.id
                having m_count > ?
                order by m_count desc;""");

        preparedStatement.setInt(1, 15);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getInt("m_count"));
        }

        connection.close();
    }
}
