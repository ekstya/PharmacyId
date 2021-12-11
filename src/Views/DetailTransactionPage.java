package Views;

import Model.TransactionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class DetailTransactionPage extends MyFrame implements ActionListener {
    private final int cashierId;
    JButton backButton;

    DetailTransactionPage(
            int cashierId,
            String idTransaksi,
            String tanggal,
            String totalHarga,
            String kasir) throws SQLException {
        super(400, 470);
        this.cashierId = cashierId;

        JLabel title = new JLabel("Detail transaksi");
        title.setBounds(0, 10, 400, 20);
        title.setFont(title.getFont().deriveFont(18f));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        String[][] detailTransaction = TransactionModel.getDetailTransaction(Integer.parseInt(idTransaksi));
        String[] columnNames = {"Nama Barang", "Kuantitas", "Total Harga (Rp)"};

        JLabel dateLabel = new JLabel("Tanggal : " + tanggal);
        dateLabel.setBounds(10, 40, 390, 20);
        add(dateLabel);

        JLabel priceLabel = new JLabel("Total Harga : " + totalHarga);
        priceLabel.setBounds(10, 60, 390, 20);
        add(priceLabel);

        JLabel cashierLabel = new JLabel("Kasir : " + kasir);
        cashierLabel.setBounds(10, 80, 390, 20);
        add(cashierLabel);

        DefaultTableModel tableModel = new DefaultTableModel(detailTransaction, columnNames);
        JTable table = new JTable(tableModel);
        table.getTableHeader().setBackground(new Color(0x3948db));
        table.getTableHeader().setForeground(Color.white);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 110, 400, 200);
        scrollPane.getViewport().setBackground(Color.white);
        add(scrollPane);

        backButton = new JButton("Kembali");
        backButton.setBounds(10, 330, 370, 50);
        backButton.setBackground(Color.white);
        backButton.setForeground(new Color(0x3948db));
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new TransactionPage(cashierId);
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
