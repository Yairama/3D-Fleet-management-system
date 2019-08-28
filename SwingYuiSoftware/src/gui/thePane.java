package gui;

import gui.controlPane;
import gui.tabPanes;

import javax.swing.*;
import java.awt.*;

class thePane extends JPanel {

    private tabPanes tabs;
    private controlPane conPane;
    public thePane() {
        setLayout(new GridLayout(1,2,5,5));
        tabs = new tabPanes();
        conPane = new controlPane();
        this.add(conPane);
        this.add(tabs);
    }
}
