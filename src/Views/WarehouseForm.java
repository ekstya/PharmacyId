package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehouseForm extends MyFrame implements ActionListener {
    JTextField fieldNamaBarang, fieldJenisBarang, fieldStok, fieldHarga;
    JButton addButton, backButton;

    WarehouseForm() {
        super(600, 500);
        JLabel title = new JLabel("Tambah Barang");
        title.setFont(title.getFont().deriveFont(20f));
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
        fieldJenisBarang = new JTextField();
        fieldJenisBarang.setBounds(150, 170, 300, 40);
        add(fieldJenisBarang);

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
        addButton.addActionListener(this);
        add(addButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addButton) {
                System.out.println(fieldNamaBarang.getText());
                System.out.println(fieldJenisBarang.getText());
                System.out.println(fieldStok.getText());
                System.out.println(Integer.parseInt(fieldHarga.getText()));

                dispose();
                new WarehousePage();
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
