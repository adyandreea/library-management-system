package com.andreea.library_management_system.ui;

import com.andreea.library_management_system.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UserService userService;

    public LoginFrame() {
        userService = new UserService();

        setTitle("Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 170, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 170, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(110, 120, 130, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String insertUser = usernameField.getText();
                String insertPassword = new String(passwordField.getPassword());

                boolean IsSuccess = userService.authenticate(insertUser, insertPassword);

                if (IsSuccess) {
                    JOptionPane.showMessageDialog(null, "Authentication successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Username or password is incorrect");
                }
            }
        });
    }
}
