package controller;

import model.Categories;
import model.HomeData;
import model.User;
import repo.AppsRepo;
import views.AdminAddAppView;

import javax.swing.*;
import java.io.File;

public class AdminAppController extends Controller {
    AdminAddAppView view;
    public AdminAppController() {
        this.view = new AdminAddAppView();
        view.setVisible(true);
        this.view.getAddButton().addActionListener(e -> saveApp());
        this.view.getBackButton().addActionListener(e -> changeView());
    }

    private void saveApp() {
        // Collect data from the view fields
        String name = view.getNameField().getText();
        String description = view.getDescriptionField().getText();
        Categories category = (Categories) view.getCategoryComboBox().getSelectedItem();
        String appSizeType = (String) view.getAppSizeTypeComboBox().getSelectedItem();
        String appSizeNumber = view.getAppSizeNumberField().getText();
        File imageFile = view.getSelectedImageFile();

        float size = 0;
        try {
            size = Float.parseFloat(appSizeNumber);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid size number format.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Pass the data to the AppRepo to add to the database
        AppsRepo.addApp(name, description, category, appSizeType, size, imageFile);

        JOptionPane.showMessageDialog(view, "App added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        changeView();
    }


    @Override
    void changeView() {
        //go home
        HomeData.allApps.clear();
        User.usersApps.clear();
        this.view.setVisible(false);
        new HomePageController();
    }


}
