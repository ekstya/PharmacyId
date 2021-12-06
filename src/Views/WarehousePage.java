package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehousePage extends MyFrame implements ActionListener {
    JButton backButton;
    JButton addButton;
    JButton updateButton;

    WarehousePage() {
        super(600, 420);

        JLabel title = new JLabel("Gudang");
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(0, 30, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);


        String[][] data = {
                {"1", "Paracetamol", "Paracetamol", "21", "100000"},
                {"1", "Paracetamol", "Paracetamol", "21", "100000"},
                {"1", "Paracetamol", "Paracetamol", "21", "100000"},
                {"1", "Paracetamol", "Paracetamol", "21", "100000"},
                {"1", "Paracetamol", "Paracetamol", "21", "100000"},
                {"1", "Paracetamol", "Paracetamol", "21", "100000"}
        };

        String[] columnNames = {"id", "Nama Barang", "Jenis", "Stok", "Harga (Rp)"};

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.getTableHeader().setBackground(new Color(0x3948db));
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setMaxWidth(8);

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
        if (e.getSource() == backButton) {
            dispose();
            new DashboardPage();
        } else if (e.getSource() == addButton) {
            new WarehouseForm();
        }
    }
}
