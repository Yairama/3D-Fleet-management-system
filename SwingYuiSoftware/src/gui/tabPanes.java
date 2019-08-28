package gui;

import gui.oglCanvas;
import gui.svgJpane;

import javax.swing.*;

public class tabPanes extends JTabbedPane {
    public static svgJpane svgPane = new svgJpane();
    public static oglCanvas dxfPane = new oglCanvas();
    public tabPanes() {
        this.addTab("SVGView",svgPane);
        this.addTab("DXFView",dxfPane);
    }
}
