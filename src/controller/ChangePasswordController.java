package controller;

import model.HomeData;
import model.User;
import repo.UserRepo;
import views.ChangePasswordView;
import javax.swing.*;
import java.util.Arrays;

public class ChangePasswordController extends Controller{
    private ChangePasswordView view;

    public ChangePasswordController() {
        this.view = new ChangePasswordView();
        this.view.setVisible(true);
        initController();
    }

    private void initController() {
        view.getChangePasswordButton().addActionListener(e -> attemptPasswordChange());
        view.getBackButton().addActionListener(e -> changeView());
    }

    private void attemptPasswordChange() {
        // Retrieve password values from the view
        String oldPassword = Arrays.toString(view.getOldPasswordField().getPassword());
        String newPassword = Arrays.toString(view.getNewPasswordField().getPassword());
        String confirmNewPassword = Arrays.toString(view.getConfirmNewPasswordField().getPassword());

        // Check if new passwords match
        if (!newPassword.equals(confirmNewPassword)) {
            JOptionPane.showMessageDialog(view, "New passwords do not match.", "Change Password Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if new password is different from the old password
        if (oldPassword.equals(newPassword)) {
            JOptionPane.showMessageDialog(view, "New password must be different from the old password.", "Change Password Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check with the repo if the old password is correct
        if (!UserRepo.checkCredentials(User.username, oldPassword)) {
            JOptionPane.showMessageDialog(view, "The old password is incorrect.", "Change Password Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // At this point, all checks have passed, and we can attempt to change the password
        boolean passwordChanged = UserRepo.changePassword(newPassword);
        if (passwordChanged) {
            JOptionPane.showMessageDialog(view, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            changeView();
        } else {
            JOptionPane.showMessageDialog(view, "Password could not be changed due to an error.", "Change Password Error", JOptionPane.ERROR_MESSAGE);
        }

        view.getOldPasswordField().setText("");
        view.getNewPasswordField().setText("");
        view.getConfirmNewPasswordField().setText("");
    }

    @Override
    void changeView() {
        User.usersApps.clear();
        HomeData.allApps.clear();
        this.view.setVisible(false);
        new HomePageController();
    }
}
