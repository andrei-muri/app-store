package controller;

import javax.swing.*;

public class Controller {
    JFrame view;

    public Controller() {
        this.view = new JFrame();
    }

    void changeView(String dest) {
        this.view.dispose();
    }
    void changeView() {
        this.view.dispose();
    }
}
