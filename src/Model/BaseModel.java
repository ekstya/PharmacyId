package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseModel {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    public Connection connection;

    public BaseModel(){
        dbUrl = "jdbc:postgresql://localhost/PharmacyId";
        dbUser = "postgres";
        dbPassword = "eka280602";

        try {
            connection = DriverManager.getConnection(dbUrl,dbUser, dbPassword);
            System.out.println("Berhasil");
        }catch (SQLException e){
            System.out.println("Terjadi Kesalahan : "+e.getMessage());
        }
    }
}
