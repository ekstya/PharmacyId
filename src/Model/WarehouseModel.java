package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WarehouseModel extends BaseModel {

    public WarehouseModel() throws SQLException {
        super();
        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS jenis_barang(" +
                        "id SERIAL PRIMARY KEY," +
                        "nama_jenis VARCHAR(30) NOT NULL" +
                        ")"
        );
        ps.executeUpdate();

        ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS barang(" +
                        "id SERIAL PRIMARY KEY," +
                        "nama_barang VARCHAR(30) NOT NULL," +
                        "harga INTEGER NOT NULL," +
                        "stok INTEGER NOT NULL," +
                        "fk_id_jenis_barang INTEGER NOT NULL REFERENCES jenis_barang(id)" +
                        ")"
        );
        ps.executeUpdate();

        ps.close();
    }
}
