package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
