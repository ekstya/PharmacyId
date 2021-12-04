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
}
