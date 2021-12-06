import Model.AuthModel;
import Model.TransactionModel;
import Model.WarehouseModel;
import Views.LoginPage;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new LoginPage();

        try {
            new AuthModel();
            new WarehouseModel();
            new TransactionModel();
            System.out.println("Inisialisasi database berhasil");
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan\n" + e.getMessage());
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage(),
                    "error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    int getSomething() {
        return 1;
    }
}