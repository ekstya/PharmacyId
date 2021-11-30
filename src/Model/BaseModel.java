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
        dbUrl = "jdbc:postgresql://localhost/pharmacyId";
        dbUser = "postgres";
        dbPassword = "postgres";

        try {
            connection = DriverManager.getConnection(dbUrl,dbUser, dbPassword);
            System.out.println("Berhasil terhubung ke database");
        }catch (SQLException except){
            System.out.println("Terjadi Kesalahan dalam database: "+except.getMessage());
        }
    }
}
