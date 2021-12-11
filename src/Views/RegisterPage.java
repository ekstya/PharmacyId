package Views;

import Model.AuthModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

class RegisterPage extends MyFrame implements ActionListener {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton registerButton, loginButton;

    public RegisterPage() {
        super(600, 420);

        JLabel title = new JLabel("Registrasi");
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

        registerButton = new JButton("Registrasi");
        registerButton.setBounds(150, 250, 300, 44);
        registerButton.setBackground(new Color(0x3948db));
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(this);
        add(registerButton);

        loginButton = new JButton("Masuk");
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
                String username = usernameField.getText();
                String password = Arrays.toString(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    throw new Exception("Inputan harus terisi");
                }
                AuthModel.addAccount(username, password);
                JOptionPane.showMessageDialog(null, "Pendaftaran akun berhasil");
                dispose();
                new LoginPage();
            }
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }}