package views;

import model.Categories;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminAddAppView extends AbstractView {

    private JTextField nameField;
    private JTextField appSizeNumberField;
    private JComboBox<String> appSizeTypeComboBox;
    private JTextArea descriptionField;
    private JComboBox<Categories> categoryComboBox;
    private JButton chooseImageButton;
    private JLabel imageLabel;
    private JButton addButton;
    private JButton backButton;
    private File selectedImageFile;

    public AdminAddAppView() {
        setTitle("Add New App");
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);


        nameField = new JTextField(20);
        addLabeledField("Name: ", nameField, gbc);

        appSizeNumberField = new JTextField(20);
        addLabeledField("Size: ", appSizeNumberField, gbc);

        appSizeTypeComboBox = new JComboBox<>(new String[]{"MB", "GB"});
        addLabeledField("Size Type: ", appSizeTypeComboBox, gbc);

        descriptionField = new JTextArea(5, 20);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(descriptionField);
        addLabeledField("Description: ", scrollPane, gbc);

        categoryComboBox = new JComboBox<>(Categories.values());
        addLabeledField("Category: ", categoryComboBox, gbc);

        chooseImageButton = new JButton("Choose Image");
        chooseImageButton.addActionListener(e -> chooseImage());
        imageLabel = new JLabel();
        addLabeledField("Image: ", chooseImageButton, gbc);
        addLabeledField("Selected Image: ", imageLabel, gbc);

        addButton = new JButton("Add App");
        backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        setVisible(false);
    }

    private void addLabeledField(String labelText, Component field, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        add(label, gbc);
        gbc.gridx++;
        add(field, gbc);
        gbc.gridx--;
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            imageLabel.setText(selectedImageFile.getName());
        }
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAppSizeNumberField() {
        return appSizeNumberField;
    }

    public JComboBox<String> getAppSizeTypeComboBox() {
        return appSizeTypeComboBox;
    }

    public JTextArea getDescriptionField() {
        return descriptionField;
    }

    public JComboBox<Categories> getCategoryComboBox() {
        return categoryComboBox;
    }

    public File getSelectedImageFile() {
        return selectedImageFile;
    }
}
