package gui;

import gui.MarcoPrincipal;

import javax.swing.*;
import java.awt.*;

public class MainYui {

    private JPanel principalJpanel;

    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        MarcoPrincipal principalFrame = new MarcoPrincipal(toolkit.getScreenSize(),"gui.MainYui");
        principalFrame.setVisible(true);
        principalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
