package views;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordView extends AbstractView {
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmNewPasswordField;
    private JButton changePasswordButton;
    private JButton backButton;

    public ChangePasswordView() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setTitle("Change Password");
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

       oldPasswordField = new JPasswordField(20);
        addLabeledField("Old Password:", oldPasswordField, gbc);

        newPasswordField = new JPasswordField(20);
        addLabeledField("New Password:", newPasswordField, gbc);

        confirmNewPasswordField = new JPasswordField(20);
        addLabeledField("Confirm New Password:", confirmNewPasswordField, gbc);

        changePasswordButton = new JButton("Change Password");
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(changePasswordButton, gbc);

        backButton = new JButton("Back");
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(backButton, gbc);
        setVisible(false);
    }

    private void addLabeledField(String labelText, Component field, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        add(label, gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(field, gbc);
        gbc.gridx--;
    }

    public JPasswordField getOldPasswordField() {
        return oldPasswordField;
    }

    public JPasswordField getNewPasswordField() {
        return newPasswordField;
    }

    public JPasswordField getConfirmNewPasswordField() {
        return confirmNewPasswordField;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
