package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthModel extends BaseModel {

    public AuthModel() throws SQLException {
        super();
        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS kasir(" +
                        "id SERIAL PRIMARY KEY," +
                        "username VARCHAR(30) NOT NULL UNIQUE," +
                        "password VARCHAR(30) NOT NULL" +
                        ")"
        );
        ps.executeUpdate();

        ps.close();
    }

    public static void addAccount(String username, String password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO kasir"
                + "(username, password)"
                + "VALUES (?,?)");

        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        ps.close();

    }

    public static int getAccount(String username, String password) throws SQLException {
        int id = -1;

        PreparedStatement ps = connection.prepareStatement(
                "SELECT id FROM kasir " +
                        "WHERE " +
                        "username=? " +
                        "AND " +
                        "password=?"
        );
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();


        if (rs.next()) {
            id = rs.getInt("id");
        }
        ps.close();
        return id;
    }
}
