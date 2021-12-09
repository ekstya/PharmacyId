package Views;

import Model.WarehouseModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

public class WarehousePage extends MyFrame implements ActionListener {
    JButton addButton, backButton, updateButton;
    JTable table;

    WarehousePage() throws SQLException {
        super(600, 420);

        JLabel title = new JLabel("Gudang");
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(0, 30, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        String[][] products = WarehouseModel.getAllProduct();
        Arrays.sort(products, Comparator.comparingInt(o -> Integer.parseInt(o[0])));
        String[] columnNames = {"id", "Nama Barang", "Jenis", "Stok", "Harga (Rp)"};
        DefaultTableModel tableModel = new DefaultTableModel(products, columnNames);
        table = new JTable(tableModel);
        table.getTableHeader().setBackground(new Color(0x3948db));
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setMaxWidth(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 80, 600, 200);
        scrollPane.getViewport().setBackground(Color.white);
        add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 300, 540, 50);
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));

        backButton = new JButton("Kembali");
        backButton.setBackground(Color.white);
        backButton.setForeground(new Color(0x3948db));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        addButton = new JButton("Tambah");
        addButton.setBackground(new Color(0x3948db));
        addButton.setForeground(Color.white);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        updateButton = new JButton("Ubah");
        updateButton.setBackground(new Color(0x3948db));
        updateButton.setForeground(Color.white);
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);

        add(buttonPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == backButton) {
                new DashboardPage();
            } else if (e.getSource() == addButton) {
                new WarehouseForm();
            } else if (e.getSource() == updateButton) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    throw new Exception("Pilih baris yang ingin diubah");
                }
                int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                String namaBarang = table.getValueAt(selectedRow, 1).toString();
                String jenisBarang = table.getValueAt(selectedRow, 2).toString();
                String stok = table.getValueAt(selectedRow, 3).toString();
                String harga = table.getValueAt(selectedRow, 4).toString();
                new WarehouseForm().setUpdate(id, namaBarang, jenisBarang, stok, harga);
            }
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
        }
    }
}
