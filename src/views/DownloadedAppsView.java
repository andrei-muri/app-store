package views;

import model.App;
import model.User;

import javax.swing.*;
import java.awt.*;

public class DownloadedAppsView extends AbstractView {
    private JPanel appsPanel;
    private JScrollPane appsScrollPane;
    private JButton backButton;

    public DownloadedAppsView() {
        super();
        createView();
        setTitle("Downloaded Apps");
        setLocationRelativeTo(null);
    }

    private void createView() {
        setLayout(new BorderLayout());
        backButton = new JButton("Back");


        appsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (App app : User.usersApps) {
            AppPanel appPanel = new AppPanel(app, "Download", this);
            appsPanel.add(appPanel);
        }

        appsScrollPane = new JScrollPane(appsPanel);
        appsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        appsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(backButton, BorderLayout.NORTH);
        add(appsScrollPane, BorderLayout.CENTER);
        setVisible(false);
    }

    public JButton getBackButton() {
        return backButton;
    }
}
