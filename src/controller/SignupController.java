package controller;

import repo.UserRepo;
import views.SignupView;

import javax.swing.*;
import java.util.Arrays;

public class SignupController extends Controller {
    SignupView view;

    public SignupController() {
        this.view = new SignupView();
        this.view.setVisible(true);
        this.view.getSignupButton().addActionListener(e -> signup());
    }

    private void signup() {
        String username = view.getUsernameField().getText();
        char[] password = view.getPasswordField().getPassword();
        char[] confirmPassword = view.getConfirmPasswordField().getPassword();
        boolean isAdmin = view.isAdmin();
        char[] adminPassword = isAdmin ? view.getAdminPasswordField().getPassword() : null;

        //username is taken
        if (UserRepo.isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(view, "Username is already taken.", "Signup Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Arrays.equals(password, confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Passwords do not match.", "Signup Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isAdmin && (!Arrays.equals(adminPassword, "admin".toCharArray()))) {
            JOptionPane.showMessageDialog(view, "Invalid admin password.", "Signup Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserRepo.addUser(username, Arrays.toString(password), isAdmin);
        JOptionPane.showMessageDialog(view, "Nice! Now login!");

        changeView();
    }

    @Override
    void changeView() {
        //go to log in
        view.setVisible(false);
        new LoginController();
    }
}
