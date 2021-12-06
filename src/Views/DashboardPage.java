package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DashboardPage extends MyFrame implements ActionListener {
    JLabel title = new JLabel("DASHBOARD");

    JButton gudang = new MenuButton("Gudang", "./Assets/package.png");
    JButton transaksi = new MenuButton("Transaksi", "./Assets/shopping-cart.png");
    JButton logout = new MenuButton("Log Out", "./Assets/log-out.png");


    DashboardPage() {
        super(600, 420);
        setLayout(new BorderLayout());

        title.setFont(title.getFont().deriveFont(24f));
        title.setPreferredSize(new Dimension(600, 80));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.BOTTOM);
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 60, 20));
        panel.setLayout(new GridLayout(0, 3, 20, 0));
        panel.setBackground(Color.white);

        gudang.addActionListener(this);
        panel.add(gudang);
        transaksi.addActionListener(this);
        panel.add(transaksi);
        logout.addActionListener(this);
        panel.add(logout);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gudang) {
            dispose();
            new WarehousePage();
        } else if (e.getSource() == logout) {
            dispose();
            new LoginPage();
        } else {
            JOptionPane.showMessageDialog(null, "Belum dapat diimplementasikan");
        }
    }
}


class MenuButton extends JButton {

    MenuButton(String text, String imagePath) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                getClass().getResource(imagePath))
        );
        Image resizeIcon = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeIcon);


        setText(text);
        setIcon(icon);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(new Color(0x3948db), 1));
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(BOTTOM);
        setIconTextGap(20);
        setBackground(Color.white);
        setForeground(new Color(0x3948db));
    }
}

