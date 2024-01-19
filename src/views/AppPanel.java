package views;

import controller.DownloadedAppsController;
import controller.HomePageController;
import model.App;
import model.HomeData;
import model.User;
import repo.AppsRepo;

import javax.swing.*;
import java.awt.*;

class AppPanel extends JPanel {
    App app;
    private JLabel nameLabel;

    private JLabel imageLabel;
    private JButton downloadButton;

    public AppPanel(App app, String src, JFrame view) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        nameLabel = new JLabel(app.name());
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nameLabel, BorderLayout.CENTER);

        String imagePath = app.img();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        imageLabel = new JLabel(imageIcon);
        add(imageLabel, BorderLayout.NORTH);

        if(src.equals("Download")) {
            downloadButton = new JButton("Uninstall");
            downloadButton.addActionListener(e -> uninstallApp(app, view));

            add(downloadButton, BorderLayout.SOUTH);
        } else if(!app.isDownloaded()){
            downloadButton = new JButton("Download");
            downloadButton.addActionListener(e -> downloadApp(app.appId(), view));
            if(!User.isAdmin) {
                add(downloadButton, BorderLayout.SOUTH);
            }
        }
    }

    private void uninstallApp(App app, JFrame view) {
        User.usersApps.clear();
        HomeData.allApps.clear();
        view.setVisible(false);
        AppsRepo.uninstallApp(app.appId());
        new DownloadedAppsController();
    }

    private void downloadApp(int appId, JFrame view) {
        User.usersApps.clear();
        HomeData.allApps.clear();
        view.setVisible(false);
        AppsRepo.addDownloadedApp(appId);
        new HomePageController();

    }

    public JButton getDownloadButton() {
        return downloadButton;
    }
}
