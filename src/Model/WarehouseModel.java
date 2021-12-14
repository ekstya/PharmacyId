package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseModel extends BaseModel {

    public WarehouseModel() throws SQLException {
        super();
        PreparedStatement ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS jenis_barang(" +
                        "id SERIAL PRIMARY KEY," +
                        "nama_jenis VARCHAR(30) UNIQUE NOT NULL" +
                        ")"
        );
        ps.executeUpdate();

        ps = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS barang(" +
                        "id SERIAL PRIMARY KEY," +
                        "nama_barang VARCHAR(70) UNIQUE NOT NULL," +
                        "harga INTEGER NOT NULL," +
                        "stok INTEGER NOT NULL," +
                        "fk_id_jenis_barang INTEGER NOT NULL REFERENCES jenis_barang(id)" +
                        ")"
        );
        ps.executeUpdate();

        ps.close();
    }


    public static String[][] getAllProduct() throws SQLException {
        ArrayList<String[]> products = new ArrayList<String[]>();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM barang"
        );
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String[] line = new String[5];
            line[0] = rs.getString("id");
            line[1] = rs.getString("nama_barang");
            String jenisBarang = "Unknown";
            ps = connection.prepareStatement(
                    "SELECT * FROM jenis_barang WHERE id=?"
            );
            ps.setInt(1, rs.getInt("fk_id_jenis_barang"));
            ResultSet rsJenisBarang = ps.executeQuery();
            if (rsJenisBarang.next()) {
                jenisBarang = rsJenisBarang.getString("nama_jenis");
            }
            line[2] = jenisBarang;
            line[3] = rs.getString("stok");
            line[4] = rs.getString("harga");

            products.add(line);
        }

        ps.close();
        return products.toArray(new String[0][0]);
    }


    public static String[] getAllProductTypes() throws SQLException {
        ArrayList<String> productsTypes = new ArrayList<String>();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT nama_jenis FROM jenis_barang"
        );
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            productsTypes.add(rs.getString("nama_jenis"));
        }

        ps.close();
        return productsTypes.toArray(new String[0]);
    }


    public static void addProductType(String namaJenis) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO jenis_barang" +
                        "(nama_jenis)" +
                        "VALUES(?)"
        );
        ps.setString(1, namaJenis);
        ps.executeUpdate();
        ps.close();
    }


    public static void addProduct(String namaBarang, int harga, int stok, String jenisBarang) throws SQLException {
        int fk_id_jenis_barang = getJenisBarangFK(jenisBarang);
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO barang" +
                        "(nama_barang, harga, stok, fk_id_jenis_barang)" +
                        "VALUES(?, ?, ?, ?)"
        );
        ps.setString(1, namaBarang);
        ps.setInt(2, harga);
        ps.setInt(3, stok);
        ps.setInt(4, fk_id_jenis_barang);
        ps.executeUpdate();
        ps.close();
    }


    public static void updateProduct
            (int id,
             String namaBarang,
             int harga,
             int stok,
             String jenisBarang)
            throws SQLException {
        int fk_id_jenis_barang = getJenisBarangFK(jenisBarang);
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE barang " +
                        "SET (nama_barang, harga, stok, fk_id_jenis_barang) = (?, ?, ?, ?) " +
                        "WHERE id = ?"
        );
        ps.setString(1, namaBarang);
        ps.setInt(2, harga);
        ps.setInt(3, stok);
        ps.setInt(4, fk_id_jenis_barang);
        ps.setInt(5, id);
        ps.executeUpdate();

        ps.close();
    }


    private static int getJenisBarangFK(String jenisBarang) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT id FROM jenis_barang WHERE nama_jenis=?"
        );
        ps.setString(1, jenisBarang);
        ResultSet rs = ps.executeQuery();
        int fk_id_jenis_barang;
        if (rs.next()) {
            fk_id_jenis_barang = rs.getInt("id");
        } else {
            throw new SQLException("id tidak ditemukan");
        }

        ps.close();
        return fk_id_jenis_barang;
    }
}
