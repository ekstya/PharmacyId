package Views;

import Model.AuthModel;
import Model.WarehouseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RegisterPage extends MyFrame implements ActionListener {
    JLabel title = new JLabel("REGISTRASI");

    JLabel usernameLabel = new JLabel("Username");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();

    JButton registerButton = new JButton("Registrasi");
    JButton loginButton = new JButton("Masuk");

    public RegisterPage() {
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

        registerButton.setFocusable(false);
        registerButton.setBounds(150, 250, 300, 44);
        registerButton.setBackground(new Color(0x3948db));
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(this);
        add(registerButton);

        loginButton.setFocusable(false);
        loginButton.setBounds(150, 300, 300, 40);
        loginButton.setBackground(Color.white);
        loginButton.setForeground(new Color(0x3948db));
        loginButton.addActionListener(this);
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loginButton) {
                dispose();
                new LoginPage();

            } else if (e.getSource() == registerButton) {
                JOptionPane.showMessageDialog(
                        null,
                        "Registrasi akun berhasil",
                        "WOW!",
                        JOptionPane.INFORMATION_MESSAGE);
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    throw new Exception("Inputan harus terisi");
                }
                AuthModel.addAccount(username, password);
                dispose();
                new LoginPage();
            }
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception);
        }
    }}