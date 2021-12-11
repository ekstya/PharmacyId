package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionModel extends BaseModel {

    public TransactionModel() throws SQLException {
        super();

        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS transaksi(" +
                        "id SERIAL PRIMARY KEY," +
                        "tanggal_transaksi DATE NOT NULL," +
                        "fk_id_kasir INTEGER NOT NULL REFERENCES kasir(id)" +
                        ")"
        );
        ps.executeUpdate();

        ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS detail_transaksi(" +
                        "id SERIAL PRIMARY KEY," +
                        "quantity INTEGER NOT NULL," +
                        "total INTEGER NOT NULL," +
                        "fk_id_barang INTEGER NOT NULL REFERENCES barang(id)," +
                        "fk_id_transaksi INTEGER NOT NULL REFERENCES transaksi(id)" +
                        ")"
        );
        ps.executeUpdate();

        ps.close();
    }


    public static String[][] getAllTransaction() throws SQLException {
        ArrayList<String[]> transactions = new ArrayList<String[]>();

        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM transaksi"
        );
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String[] line = new String[4];
            line[0] = rs.getString("id");
            line[1] = rs.getString("tanggal_transaksi");

            ps = connection.prepareStatement(
                    "SELECT total FROM detail_transaksi " +
                            "WHERE fk_id_transaksi=?"
            );
            ps.setInt(1, rs.getInt("id"));
            ResultSet rsDetailTransaksi = ps.executeQuery();

            int totalHarga = 0;
            while(rsDetailTransaksi.next()) {
                totalHarga += rsDetailTransaksi.getInt("total");
            }

            line[2] = String.format("%d", totalHarga);

            ps = connection.prepareStatement(
                    "SELECT username FROM kasir " +
                            "WHERE id=?"
            );
            ps.setInt(1, rs.getInt("fk_id_kasir"));
            ResultSet rsKasir = ps.executeQuery();
            rsKasir.next();
            line[3] = rsKasir.getString("username");

            transactions.add(line);
        }
        ps.close();
        return transactions.toArray(new String[0][0]);
    }


    public static String[][] getDetailTransaction(int transactionId) throws SQLException {
        ArrayList<String[]> detailTransaction = new ArrayList<String[]>();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM detail_transaksi " +
                        "WHERE id=?"
        );
        ps.setInt(1, transactionId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String[] line = new String[3];

            ps = connection.prepareStatement(
                    "SELECT nama_barang FROM barang " +
                            "WHERE id=?"
            );
            ps.setInt(1, rs.getInt("fk_id_barang"));
            ResultSet rsBarang = ps.executeQuery();
            rsBarang.next();
            line[0] = rsBarang.getString("nama_barang");

            line[1] = rs.getString("quantity");
            line[2] = rs.getString("total");

            detailTransaction.add(line);
        }
        ps.close();
        return detailTransaction.toArray(new String[0][0]);
    }


    public static void addTransaction(int cashierId, int[][] dataTransaksi) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO transaksi" +
                        "(tanggal_transaksi, fk_id_kasir) " +
                        "VALUES(?, ?)"
                , PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(2, cashierId);
        int affectedRow = ps.executeUpdate();

        if (affectedRow == 0) {
            throw new SQLException("Pencatatan data transaksi gagal");
        }

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int transactionId = rs.getInt(1);

        for (int[] data : dataTransaksi) {
            ps = connection.prepareStatement(
                    "INSERT INTO detail_transaksi" +
                            "(quantity, total, fk_id_barang, fk_id_transaksi) " +
                            "VALUES(?, ?, ?, ?)"
            );
            ps.setInt(1, data[1]);
            ps.setInt(2, data[2]);
            ps.setInt(3, data[0]);
            ps.setInt(4, transactionId);
            ps.executeUpdate();

            ps = connection.prepareStatement(
                    "SELECT stok FROM barang"
            );
            rs = ps.executeQuery();
            rs.next();
            ps = connection.prepareStatement(
                    "UPDATE barang " +
                            "SET stok = ? " +
                            "WHERE id = ?"
            );
            ps.setInt(1, rs.getInt("stok") - data[1]);
            ps.setInt(2, data[0]);
            ps.executeUpdate();
        }

        ps.close();
    }
}
