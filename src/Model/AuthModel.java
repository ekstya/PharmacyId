package Model;


import Views.LoginPage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthModel extends BaseModel {
    public static int val;

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

    public static void getAccount(String username, String password) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * from kasir where username=?AND password=?");

        ps.setString(1,username);
        ps.setString(2,password);

        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            val = 1;

        }else{
            val=2;
        }


    }}
