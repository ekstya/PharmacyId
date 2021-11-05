package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {
    JFrame frame = new JFrame();
    JLabel title = new JLabel("Selamat datang di Pharmacy.Id");
    JLabel instruction = new JLabel("Silahkan login terlebih dahulu");
    JTextField usernameField = new JTextField("masukkan username anda");
    JTextField passwordField = new JTextField("masukkan password anda");
    JButton loginButton = new JButton("Login");


    public LoginPage(){
        title.setFont(title.getFont().deriveFont(24f));
        title.setBounds(0, 20, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        frame.add(title);

        instruction.setBounds(0, 60, 600, 20);
        instruction.setHorizontalAlignment(JLabel.CENTER);
        frame.add(instruction);

        usernameField.setBounds(150, 90, 300, 40);
        frame.add(usernameField);

        passwordField.setBounds(150, 140, 300, 40);
        frame.add(passwordField);

        loginButton.setFocusable(false);
        loginButton.setBounds(150, 200, 300, 44);
        loginButton.setBackground(new Color(0x3948db));
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        frame.setTitle("Pharmacy.Id");
        frame.setLayout(null);
        frame.setSize(600, 320);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton){
            JOptionPane.showMessageDialog(
                    null,
                    "Belom dapat diimplementasikan",
                    "WOW!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
