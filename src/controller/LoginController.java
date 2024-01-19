package controller;

import model.HomeData;
import model.User;
import repo.UserRepo;
import views.LoginView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginController extends Controller{
    LoginView view;


    public LoginController() {
        this.view = new LoginView();
        this.view.setVisible(true);
        this.view.getLoginButton().addActionListener(e -> onLogin());
        this.view.getSignupButton().addActionListener(e -> changeView("SIGNUP"));
    }

    private void onLogin() {
        String username = view.getUsernameField().getText();
        char[] password = view.getPasswordField().getPassword();

        //check if fields are empty
        if(Arrays.toString(password).isEmpty() || username.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Empty fields!", "Signup Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //check details
        if(!UserRepo.checkCredentials(username, Arrays.toString(password))) {
            JOptionPane.showMessageDialog(view, "Wrong credentials.", "Signup Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User.username = username;
        JOptionPane.showMessageDialog(view, "Success!");
        changeView("HOME");
    }

    @Override
    void changeView(String dest) {
        String destination = dest.toUpperCase();

        switch(destination) {
            case "HOME":
                User.usersApps = new ArrayList<>();
                HomeData.allApps = new ArrayList<>();
                this.view.setVisible(false);
                new HomePageController();
                break;
            case "SIGNUP":
                this.view.setVisible(false);
                new SignupController();
                break;
        }
    }
}
