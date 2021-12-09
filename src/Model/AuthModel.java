package Model;

import java.sql.PreparedStatement;
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

    public static void addAccount(String username, String password) throws SQLException{
//        String sql = ("INSERT INTO kasir"" + "(username, password)" + "VALUES (?,?)");
        PreparedStatement ps = connection.prepareStatement("INSERT INTO kasir"
                + "(username, password)"
                + "VALUES (?,?)");

        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        ps.close();


    }
}
