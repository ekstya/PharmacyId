package Model;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryModel extends BaseModel{

    public QueryModel() throws SQLException{
        super();
    }

    public static void registration(String username, String password) throws SQLException {
        String query = "INSERT INTO userAccount" + "(username, password)"+ "VALUES (?,?)";

        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1,username);
        prepareStatement.setString(2,password);

        prepareStatement.executeUpdate();
    }
}
