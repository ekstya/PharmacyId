package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends MyFrame implements ActionListener {
    JLabel title = new JLabel("MASUK");

    JLabel usernameLabel = new JLabel("Username");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();

    JButton loginButton = new JButton("Masuk");
    JButton registerButton = new JButton("Registrasi");

    public LoginPage() {
        super(600, 420);
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(0, 30, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        usernameLabel.setBounds(150, 80, 300, 20);
        add(usernameLabel);
        usernameField.setBounds(150, 100, 300, 40);
        add(usernameField);

        passwordLabel.setBounds(150, 150, 300, 20);
        add(passwordLabel);
        passwordField.setBounds(150, 170, 300, 40);
        add(passwordField);

        loginButton.setFocusable(false);
        loginButton.setBounds(150, 250, 300, 40);
        loginButton.setBackground(new Color(0x3948db));
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton.setFocusable(false);
        registerButton.setBounds(150, 300, 300, 44);
        registerButton.setBackground(Color.white);
        registerButton.setForeground(new Color(0x3948db));
        registerButton.addActionListener(this);
        add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            dispose();
            new DashboardPage();
        } else if (e.getSource() == registerButton) {
            dispose();
            new RegisterPage();
        }
    }
}