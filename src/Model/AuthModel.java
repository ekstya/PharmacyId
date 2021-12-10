package Model;

import javax.swing.*;
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

    public static void addAccount(String username, String password) throws SQLException{
        PreparedStatement ps = connection.prepareStatement("INSERT INTO kasir"
                + "(username, password)"
                + "VALUES (?,?)");

        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        ps.close();

    }

    public static void getAccount(String username, String password) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * from kasir where username='"+username+"'AND password='"+password+"'");
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            if(username.equals(rs.getString("username")) && password.equals(rs.getString("password"))){
                JOptionPane.showMessageDialog(
                        null,
                        "Berhasil Login");
            }
            else {
                JOptionPane.showMessageDialog(
                        null,
                        "username atau password salah"
                );
            }
        }
        ps.close();
    }

}
