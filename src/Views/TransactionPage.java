package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionPage extends MyFrame implements ActionListener {
    JTable table;
    JButton addButton, backButton, detailButton;

    TransactionPage() {
        super(600, 420);
        JLabel title = new JLabel("TRANSAKSI");
        title.setFont(title.getFont().deriveFont(20f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 30, 600, 24);
        add(title);

        String[][] DUMMY_DATA = {
                {"1", "DUMMY", "DUMMY", "DUMMY"},
                {"1", "DUMMY", "DUMMY", "DUMMY"},
                {"1", "DUMMY", "DUMMY", "DUMMY"},
                {"1", "DUMMY", "DUMMY", "DUMMY"}
        };
        String[] columnNames = {"id", "tanggal", "total", "kasir"};
        DefaultTableModel tableModel = new DefaultTableModel(DUMMY_DATA, columnNames);
        table = new JTable(tableModel);
        table.getTableHeader().setBackground(new Color(0x3948db));
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setMaxWidth(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 75, 600, 200);
        scrollPane.getViewport().setBackground(Color.white);
        add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 300, 540, 50);
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBackground(Color.white);

        backButton = new JButton("Kembali");
        backButton.setForeground(new Color(0x3948db));
        backButton.setBackground(Color.white);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        detailButton = new JButton("Lihat Detail");
        detailButton.setForeground(Color.white);
        detailButton.setBackground(new Color(0x3948db));
        detailButton.addActionListener(this);
        buttonPanel.add(detailButton);

        addButton = new JButton("Tambah");
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(0x3948db));
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        add(buttonPanel);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addButton) {
                new TransactionForm();
            } else {
                new DashboardPage();
            }
            dispose();
        } catch (Exception exception) {
//            JOptionPane.showMessageDialog(null, excption);
            exception.printStackTrace();
        }
    }
}
