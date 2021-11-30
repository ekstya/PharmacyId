package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseModel {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    public static Connection connection;

    public BaseModel() throws SQLException {
        dbUrl = "jdbc:postgresql://localhost/pharmacyId";
        dbUser = "postgres";
        dbPassword = "postgres";

        connection = DriverManager.getConnection(dbUrl,dbUser, dbPassword);

    }
}
