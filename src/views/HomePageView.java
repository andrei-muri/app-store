package views;

import model.App;
import model.Categories;
import model.HomeData;
import model.User;

import javax.swing.*;
import java.awt.*;

public class HomePageView extends AbstractView {

    private JButton addButton;
    private JButton viewAppsButton;
    private JButton logoutButton;
    private JButton changePasswordButton;
    private JPanel appsPanel;
    private JScrollPane appsScrollPane;
    private JList<String> categoryList;

    public HomePageView() {
        super();
        createView();
        setTitle("AppStore");
        setLocationRelativeTo(null);
        setVisible(false);
    }

    private void createView() {
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        appsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 10, 10));
        appsPanel.setBackground(Color.WHITE);

        appsScrollPane = new JScrollPane(appsPanel);
        appsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        appsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(appsScrollPane, BorderLayout.CENTER);

        categoryList = createCategoryList();
        JScrollPane categoryScrollPane = new JScrollPane(categoryList);
        categoryScrollPane.setPreferredSize(new Dimension(150, 0));
        add(categoryScrollPane, BorderLayout.EAST);

        updateAppDisplay("All");
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.GRAY);
        headerPanel.setPreferredSize(new Dimension(800, 50));

        JLabel appStoreLabel = new JLabel("AppStore", SwingConstants.CENTER);
        appStoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        appStoreLabel.setForeground(Color.WHITE);
        headerPanel.add(appStoreLabel, BorderLayout.CENTER);

        JPanel actionsPanel = createActionsPanel();
        headerPanel.add(actionsPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createActionsPanel() {
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.setOpaque(false);

        addButton = new JButton("Add");
        viewAppsButton = new JButton("View Apps");
        logoutButton = new JButton("Logout");
        changePasswordButton = new JButton("Change Password");

        actionsPanel.add(logoutButton);
        if(User.isAdmin) {
            actionsPanel.add(addButton);
        } else {
            actionsPanel.add(viewAppsButton);
        }
        actionsPanel.add(changePasswordButton);

        return actionsPanel;
    }

    private JList<String> createCategoryList() {
        JList<String> categoryList = new JList<>(new String[]{String.valueOf(Categories.GAMES), String.valueOf(Categories.EDUCATION), String.valueOf(Categories.PRODUCTIVITY)});
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setSelectedIndex(0);
        categoryList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                String selectedCategory = categoryList.getSelectedValue();
                updateAppDisplay(selectedCategory);
            }
        });
        return categoryList;
    }

    private void updateAppDisplay(String category) {
        appsPanel.removeAll();

        for (App app : HomeData.allApps) {
            if (app.category().toString().equals(category) || category.equals("All")) {
                AppPanel appPanel = new AppPanel(app, "home", this);
                appsPanel.add(appPanel);
            }
        }

        appsPanel.revalidate();
        appsPanel.repaint();
    }

    public JButton getViewAppsButton() {
        return viewAppsButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getChangePasswordButton() {
        return changePasswordButton;
    }
}
