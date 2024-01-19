package controller;

import model.App;
import model.HomeData;
import model.User;
import repo.AppsRepo;
import views.AdminAddAppView;
import views.HomePageView;

public class HomePageController extends Controller{
    HomePageView view;

    public HomePageController() {
        AppsRepo.getAllApps();
        AppsRepo.getDownloadedApps();
        this.view = new HomePageView();
        this.view.setVisible(true);
        this.view.getViewAppsButton().addActionListener(e -> changeView("DOWNLOADED"));
        this.view.getAddButton().addActionListener(e -> changeView("ADD"));
        this.view.getLogoutButton().addActionListener(e -> changeView("LOGOUT"));
        this.view.getChangePasswordButton().addActionListener(e -> changeView("CHANGEPASSWORD"));
    }

    @Override
    void changeView(String dest) {
        String destination = dest.toUpperCase();

        switch(destination) {
            case "CHANGEPASSWORD":
                this.view.setVisible(false);
                new ChangePasswordController();
                break;
            case "DOWNLOADED":
                User.usersApps.clear();
                this.view.setVisible(false);
                new DownloadedAppsController();
                break;
            case "ADD":
                this.view.setVisible(false);
                new AdminAppController();
                break;
            case "LOGOUT":
                User.usersApps.clear();
                HomeData.allApps.clear();
                User.userId = 0;
                User.isAdmin = false;
                User.username = null;
                this.view.setVisible(false);
                new LoginController();
                break;
        }
    }
}
