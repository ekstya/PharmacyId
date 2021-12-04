package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseModel {

    protected static Connection connection;

    public BaseModel() throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost/PharmacyId";
        String dbUser = "postgres";
        String dbPassword = "postgres";

        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
