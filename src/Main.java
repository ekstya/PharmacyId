import Model.BaseModel;
import Model.QueryModel;
import Views.LoginPage;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            BaseModel model = new BaseModel();
            new LoginPage();
            QueryModel queryModel = new QueryModel();

            QueryModel.registration();
            System.out.println("Berhasil terhubung ke database");
        }catch (SQLException except){
            System.out.println("Terjadi Kesalahan dalam database: "+except.getMessage());
        }

    }
}
