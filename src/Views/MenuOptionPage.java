package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuOptionPage extends MyFrame implements ActionListener {
    int cashierId;
    JButton gudang, transaksi, logout;

    MenuOptionPage(int cashierId) {
        super(600, 420);
        this.cashierId = cashierId;

        JLabel title = new JLabel("MENU");
        title.setFont(title.getFont().deriveFont(20f));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 20, 600, 24);
        add(title);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 0, 20));
        panel.setBounds(20, 90, 550, 250);
        panel.setBackground(Color.white);

        gudang = new JButton("Gudang");
        gudang.setForeground(Color.white);
        gudang.setBackground(new Color(0x3948db));
        gudang.addActionListener(this);
        panel.add(gudang);

        transaksi = new JButton("Transaksi");
        transaksi.setForeground(Color.white);
        transaksi.setBackground(new Color(0x3948db));
        transaksi.addActionListener(this);
        panel.add(transaksi);

        logout = new JButton("Logout");
        logout.setForeground(new Color(0x3948db));
        logout.setBackground(Color.white);
        logout.addActionListener(this);
        panel.add(logout);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == gudang) {
                new WarehousePage(cashierId);
            } else if (e.getSource() == transaksi) {
                new TransactionPage(cashierId);
            } else if (e.getSource() == logout) {
                new LoginPage();
            }
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}

