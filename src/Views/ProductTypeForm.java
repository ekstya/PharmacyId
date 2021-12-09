package Views;

import Model.WarehouseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProductTypeForm extends MyFrame implements ActionListener {
    JTextField fieldJenisBarang;
    JButton addButton, backButton;

    ProductTypeForm() {
        super(600, 320);

        JLabel title = new JLabel("Tambah Jenis Barang");
        title.setFont(title.getFont().deriveFont(18f));
        title.setBounds(0, 25, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        JLabel jenisBarang = new JLabel("Jenis Barang");
        jenisBarang.setBounds(150, 80, 300, 20);
        add(jenisBarang);
        fieldJenisBarang = new JTextField();
        fieldJenisBarang.setBounds(150, 100, 300, 40);
        add(fieldJenisBarang);

        addButton = new JButton("Tambahkan");
        addButton.setBounds(150, 170, 300, 40);
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(0x3948db));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Kembali");
        backButton.setBounds(150, 230, 300, 40);
        backButton.setForeground(new Color(0x3948db));
        backButton.setBackground(Color.white);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addButton) {
                String jenisBarang = fieldJenisBarang.getText();
                WarehouseModel.addProductType(jenisBarang);
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
            }
            new WarehousePage();
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
