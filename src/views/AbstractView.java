package views;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractView extends JFrame{
    public AbstractView() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }
}
