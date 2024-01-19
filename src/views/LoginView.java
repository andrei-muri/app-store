package views;

import javax.swing.*;
import java.awt.*;

public class LoginView extends AbstractView {

    // Member components
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private JButton signupButton;

    public LoginView() {
        super();

        createView();

        setTitle("Login");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createView() {
        getContentPane().setBackground(Color.DARK_GRAY); // Set the background color
        GridBagLayout layout = new GridBagLayout();
        getContentPane().setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Insets for padding
        Insets labelInsets = new Insets(0, 10, 0, 10);
        Insets fieldInsets = new Insets(5, 10, 5, 10);

        // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        getContentPane().add(usernameLabel, gbc);

        // Username Field
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = fieldInsets;
        getContentPane().add(usernameField, gbc);

        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        getContentPane().add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        getContentPane().add(passwordField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(loginButton, gbc);

        signupButton = new JButton("Signup");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(signupButton, gbc);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}
