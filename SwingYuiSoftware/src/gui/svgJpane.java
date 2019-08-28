package gui;

import gui.inputFiles;
import org.kabeja.svg.ui.SVGViewUIComponent;
import org.kabeja.ui.UIException;

import javax.swing.*;
import java.awt.*;

public class svgJpane extends JPanel {

    private SVGViewUIComponent ui;
    public svgJpane() {
        setLayout(new BorderLayout());
        ui = new SVGViewUIComponent();
        this.add(ui.getView());
    }

    public void svgRender(){
        try {
            ui.showDXFDocument(inputFiles.doc);
        } catch (UIException e) {
            e.printStackTrace();
        }
    }
}
