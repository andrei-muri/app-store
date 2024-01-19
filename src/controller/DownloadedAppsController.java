package controller;

import model.App;
import model.HomeData;
import model.User;
import repo.AppsRepo;
import views.DownloadedAppsView;

public class DownloadedAppsController extends Controller {
    DownloadedAppsView view;

    public DownloadedAppsController() {
        //get downloaded apps through repo
        AppsRepo.getDownloadedApps();
        this.view = new DownloadedAppsView();
        this.view.setVisible(true);
        this.view.getBackButton().addActionListener(e -> changeView());
    }

    @Override
    void changeView() {
        //go home
        User.usersApps.clear();
        HomeData.allApps.clear();
        this.view.setVisible(false);
        new HomePageController();
    }
}
