package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupView extends AbstractView {

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel adminPasswordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JPasswordField adminPasswordField;
    private boolean isAdmin = false;
    private JButton signupButton;
    private JComboBox<String> userTypeComboBox;

    public SignupView() {
        super();
        createView();
        setTitle("Signup");
        setLocationRelativeTo(null);
    }

    private void createView() {
        getContentPane().setBackground(Color.DARK_GRAY);
        GridBagLayout layout = new GridBagLayout();
        getContentPane().setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        Insets fieldInsets = new Insets(5, 10, 5, 10);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User Type Combo Box
        JLabel userTypeLabel = new JLabel("User Type");
        userTypeLabel.setForeground(Color.WHITE);
        userTypeComboBox = new JComboBox<>(new String[]{"User", "Admin"});
        userTypeComboBox.addActionListener(e -> {
            this.isAdmin = userTypeComboBox.getSelectedItem().equals("Admin");
            adminPasswordLabel.setVisible(isAdmin);
            adminPasswordField.setVisible(isAdmin);
        });

        getContentPane().add(userTypeLabel, gbc);
        getContentPane().add(userTypeComboBox, gbc);

        usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);
        addLabeledField(usernameLabel, usernameField, gbc, fieldInsets);

        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);
        addLabeledField(passwordLabel, passwordField, gbc, fieldInsets);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordField = new JPasswordField(15);
        addLabeledField(confirmPasswordLabel, confirmPasswordField, gbc, fieldInsets);

        adminPasswordLabel = new JLabel("Admin Password");
        adminPasswordLabel.setForeground(Color.WHITE);
        adminPasswordLabel.setVisible(false); // Initially invisible
        adminPasswordField = new JPasswordField(15);
        adminPasswordField.setVisible(false); // Initially invisible
        addLabeledField(adminPasswordLabel, adminPasswordField, gbc, fieldInsets);

        signupButton = new JButton("Signup");
        gbc.insets = new Insets(10, 10, 5, 10);
        getContentPane().add(signupButton, gbc);
    }

    private void addLabeledField(JLabel label, Component field, GridBagConstraints gbc, Insets insets) {
        gbc.insets = insets;
        getContentPane().add(label, gbc);
        getContentPane().add(field, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SignupView();
            }
        });
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JPasswordField getAdminPasswordField() {
        return adminPasswordField;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}
