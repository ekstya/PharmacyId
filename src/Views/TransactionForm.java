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

public class TransactionForm extends MyFrame implements ActionListener {
    JTable productsTable, transactionTable;
    JTextField quantityField;
    JButton addItemButton, removeItemButton, addTransactionButton, backButton;
    String[][] products, transaction;
    DefaultTableModel transactionTableModel;

    TransactionForm() throws SQLException {
        super(600, 600);

        JLabel title = new JLabel("Tambah Transaksi");
        title.setFont(title.getFont().deriveFont(18f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 30, 600, 24);
        add(title);

        products = WarehouseModel.getAllProduct();
        Arrays.sort(products, Comparator.comparingInt(o -> Integer.parseInt(o[0])));
        String[] productsTableColumnNames = {"id", "Nama Barang", "Jenis", "Stok", "Harga (Rp)"};
        DefaultTableModel productsTableModel = new DefaultTableModel(products, productsTableColumnNames);
        productsTable = new JTable(productsTableModel);
        productsTable.getTableHeader().setBackground(new Color(0x3948db));
        productsTable.getTableHeader().setForeground(Color.white);
        productsTable.getColumnModel().getColumn(0).setMaxWidth(30);
        JScrollPane scrollPaneProducts = new JScrollPane(productsTable);
        scrollPaneProducts.getViewport().setBackground(Color.white);
        scrollPaneProducts.setBounds(0, 60, 600, 140);
        add(scrollPaneProducts);

        JLabel quantityLabel = new JLabel("Kuantitas : ");
        quantityLabel.setBounds(60, 210, 80, 40);
        quantityLabel.setVerticalAlignment(JLabel.CENTER);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(140, 210, 250, 40);
        add(quantityField);

        addItemButton = new JButton("Tambah");
        addItemButton.setBounds(400, 210, 100, 40);
        addItemButton.setForeground(new Color(0x3948db));
        addItemButton.setBackground(Color.white);
        addItemButton.addActionListener(this);
        add(addItemButton);

        String[] transactionTableColumnNames = {"id barang", "nama barang", "kuantitas", "total harga"};
        transactionTableModel = new DefaultTableModel(transaction, transactionTableColumnNames);
        transactionTable = new JTable(transactionTableModel);
        transactionTable.getTableHeader().setForeground(Color.white);
        transactionTable.getTableHeader().setBackground(new Color(0x3948db));
        JScrollPane scrollPaneTransaction = new JScrollPane(transactionTable);
        scrollPaneTransaction.getViewport().setBackground(Color.white);
        scrollPaneTransaction.setBounds(0, 270, 600, 140);
        add(scrollPaneTransaction);

        removeItemButton = new JButton("Hapus");
        removeItemButton.setBounds(400, 420, 100, 40);
        removeItemButton.setForeground(new Color(0x3948db));
        removeItemButton.setBackground(Color.white);
        removeItemButton.addActionListener(this);
        add(removeItemButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBounds(40, 500, 520, 40);
        buttonPanel.setBackground(Color.white);

        backButton = new JButton("Kembali");
        backButton.addActionListener(this);
        backButton.setForeground(new Color(0x3948db));
        backButton.setBackground(Color.white);
        buttonPanel.add(backButton);

        addTransactionButton = new JButton("Tambah Transaksi");
        addTransactionButton.addActionListener(this);
        addTransactionButton.setForeground(Color.white);
        addTransactionButton.setBackground(new Color(0x3948db));
        buttonPanel.add(addTransactionButton);

        add(buttonPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addItemButton) {
                int rowIndex = productsTable.getSelectedRow();
                if (rowIndex == -1 ){
                    throw new Exception("Pilih produk terlebih dahulu");
                }

                String namaBarang = productsTable.getValueAt(rowIndex, 1).toString();
                for (int i=0; i < transactionTable.getRowCount(); i++) {
                    if (namaBarang == transactionTable.getValueAt(i, 0)) {
                        throw new Exception("Barang sudah ditambahkan");
                    }
                }

                int stok = Integer.parseInt(productsTable.getValueAt(rowIndex, 3).toString());
                int kuantitasPesanan = Integer.parseInt(quantityField.getText());
                if (kuantitasPesanan > stok) {
                    throw new Exception("Stok tidak memenuhi");
                }

                int harga = Integer.parseInt(productsTable.getValueAt(rowIndex, 4).toString());
                String[] newTransaction = {
                        productsTable.getValueAt(rowIndex, 0).toString(),
                        namaBarang,
                        String.format("%d", kuantitasPesanan),
                        String.format("%d", harga*kuantitasPesanan)};

                transactionTableModel.addRow(newTransaction);
            } else if (e.getSource() == removeItemButton) {
                int rowIndex = transactionTable.getSelectedRow();
                if (rowIndex == -1) {
                    throw new Exception("Pilih produk yang ingin dihapus dari daftar");
                }
                transactionTableModel.removeRow(rowIndex);
            } else if (e.getSource() == backButton) {
                new TransactionPage();
                dispose();
            } else {
                throw new Exception("Fitur belum dapat diimplementasikan");
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
