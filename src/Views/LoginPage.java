package Views;

import Model.AuthModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginPage extends MyFrame implements ActionListener {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton, registerButton;

    public LoginPage() {
        super(600, 420);

        JLabel title = new JLabel("LOGIN");
        title.setFont(title.getFont().deriveFont(20f));
        title.setBounds(0, 30, 600, 24);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(150, 80, 300, 20);
        add(usernameLabel);
        usernameField.setBounds(150, 100, 300, 40);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150, 150, 300, 20);
        add(passwordLabel);
        passwordField.setBounds(150, 170, 300, 40);
        add(passwordField);

        loginButton = new JButton("Masuk");
        loginButton.setBounds(150, 250, 300, 40);
        loginButton.setBackground(new Color(0x3948db));
        loginButton.setForeground(Color.white);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton = new JButton("Registrasi");
        registerButton.setBounds(150, 300, 300, 44);
        registerButton.setBackground(Color.white);
        registerButton.setForeground(new Color(0x3948db));
        registerButton.addActionListener(this);
        add(registerButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loginButton) {
                String username = usernameField.getText();
                String password = Arrays.toString(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    throw new Exception("Inputan harus terisi");
                }

                int cashierId = AuthModel.getAccount(username, password);
                if (cashierId == -1) {
                    throw new Exception("Akun tidak terdaftar");
                }

                dispose();
                new MenuOptionPage(cashierId);
            } else if (e.getSource() == registerButton) {
                dispose();
                new RegisterPage();
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
