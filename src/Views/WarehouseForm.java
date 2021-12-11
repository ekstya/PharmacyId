package Views;

import Model.WarehouseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

class WarehouseForm extends MyFrame implements ActionListener {
    int productId, cashierId;
    JLabel title;
    JTextField fieldNamaBarang, fieldStok, fieldHarga;
    JComboBox<String> fieldJenisBarang;
    JButton addProductTypeButton, addButton, backButton, updateButton;

    WarehouseForm(int cashierId) throws SQLException {
        super(600, 520);
        this.cashierId = cashierId;

        title = new JLabel("Tambah Barang");
        title.setFont(title.getFont().deriveFont(18f));
        title.setBounds(0, 30, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        JLabel namaBarang = new JLabel("Nama Barang");
        namaBarang.setBounds(150, 80, 300, 20);
        add(namaBarang);
        fieldNamaBarang = new JTextField();
        fieldNamaBarang.setBounds(150, 100, 300, 40);
        add(fieldNamaBarang);

        JLabel jenisBarang = new JLabel("Jenis Barang");
        jenisBarang.setBounds(150, 150, 300, 20);
        add(jenisBarang);
        String[] comboBoxList = WarehouseModel.getAllProductTypes();
        if (comboBoxList.length == 0) {
            comboBoxList = new String[]{""};
        }
        fieldJenisBarang = new JComboBox<String>(comboBoxList);
        fieldJenisBarang.setBackground(Color.white);
        fieldJenisBarang.setBounds(150, 170, 250, 40);
        addProductTypeButton = new JButton("+");
        addProductTypeButton.setFont(addProductTypeButton.getFont().deriveFont(10f));
        addProductTypeButton.setBounds(410, 170, 40, 40);
        addProductTypeButton.setBackground(Color.white);
        addProductTypeButton.setForeground(new Color(0x3948db));
        addProductTypeButton.setFocusable(false);
        addProductTypeButton.addActionListener(this);
        add(fieldJenisBarang);
        add(addProductTypeButton);

        JLabel stok = new JLabel("Stok");
        stok.setBounds(150, 220, 300, 20);
        add(stok);
        fieldStok = new JTextField();
        fieldStok.setBounds(150, 240, 300, 40);
        add(fieldStok);

        JLabel harga = new JLabel("Harga (Rp)");
        harga.setBounds(150, 290, 300, 20);
        add(harga);
        fieldHarga = new JTextField();
        fieldHarga.setBounds(150, 310, 300, 40);
        add(fieldHarga);

        addButton = new JButton("Tambahkan");
        addButton.setBounds(150, 360, 300, 40);
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(0x3948db));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Kembali");
        backButton.setBounds(150, 420, 300, 40);
        backButton.setForeground(new Color(0x3948db));
        backButton.setBackground(Color.white);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    void setUpdate(int id, String namaBarang, String jenisBarang, String stok, String harga) {
        this.productId = id;
        title.setText("Ubah Data");
        fieldNamaBarang.setText(namaBarang);
        fieldJenisBarang.setSelectedItem(jenisBarang);
        fieldStok.setText(stok);
        fieldHarga.setText(harga);
        remove(addButton);

        updateButton = new JButton("Ubah");
        updateButton.setBounds(150, 360, 300, 40);
        updateButton.setForeground(Color.white);
        updateButton.setBackground(new Color(0x3948db));
        updateButton.addActionListener(this);
        add(updateButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addButton) {
                String namaBarang = fieldNamaBarang.getText();
                String jenisBarang = Objects.requireNonNull(fieldJenisBarang.getSelectedItem()).toString();
                int stok = Integer.parseInt(fieldStok.getText());
                int harga = Integer.parseInt(fieldHarga.getText());

                if (namaBarang.isEmpty() || jenisBarang.isEmpty()) {
                    throw new Exception("Inputan harus terisi");
                }

                WarehouseModel.addProduct(namaBarang, harga, stok, jenisBarang);
                new WarehousePage(cashierId);
            } else if (e.getSource() == addProductTypeButton) {
                new ProductTypeForm(cashierId);
            } else if (e.getSource() == updateButton) {
                String namaBarang = fieldNamaBarang.getText();
                String jenisBarang = Objects.requireNonNull(fieldJenisBarang.getSelectedItem()).toString();
                int stok = Integer.parseInt(fieldStok.getText());
                int harga = Integer.parseInt(fieldHarga.getText());

                WarehouseModel.updateProduct(productId, namaBarang, harga, stok, jenisBarang);
                new WarehousePage(cashierId);
            } else if (e.getSource() == backButton) {
                new WarehousePage(cashierId);
            }
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
