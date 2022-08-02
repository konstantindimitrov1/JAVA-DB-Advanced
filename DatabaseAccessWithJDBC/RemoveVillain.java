import java.sql.*;
import java.util.Scanner;

public class RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int villainId = Integer.parseInt(scanner.nextLine());

        DBConnector connector = new DBConnector();

        connector.getConnection().prepareStatement("drop procedure if exists udp_remove_villain_and_release_minions").executeUpdate();

        Statement createProcedureForDelete = connector.getConnection().createStatement();
        createProcedureForDelete.execute("""
                CREATE PROCEDURE `udp_remove_villain_and_release_minions`(villain_name varchar(20))
                BEGIN
                declare villain_id_del integer;
                set villain_id_del := (select id from villains
                where name like villain_name);

                start transaction;

                if ((select name from villains where name like villain_name) is null)
                then rollback;
                end if;

                delete from minions_villains
                where villain_id = villain_id_del;

                delete from villains
                where name like villain_name;
                commit;
                END""");

        PreparedStatement getVillainName = connector.getConnection().prepareStatement("select name from villains where id = ?");

        getVillainName.setInt(1, villainId);

        ResultSet villainNameResult = getVillainName.executeQuery();

        if (!villainNameResult.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainNameResult.getString("name");

        PreparedStatement getMinionsCount = connector.getConnection().prepareStatement("select count(*) as minions_count from minions_villains\n" +
                "where villain_id = ?;");

        getMinionsCount.setInt(1, villainId);

        ResultSet minionsCountResult = getMinionsCount.executeQuery();

        minionsCountResult.next();

        int minionsCount = minionsCountResult.getInt("minions_count");

        CallableStatement callableStatement = connector.getConnection().prepareCall("call udp_remove_villain_and_release_minions(?);");
        callableStatement.setString(1, villainName);
        callableStatement.execute();

        System.out.printf("%s was deleted\n%d minions released", villainName, minionsCount);

        connector.getConnection().close();
    }
}
