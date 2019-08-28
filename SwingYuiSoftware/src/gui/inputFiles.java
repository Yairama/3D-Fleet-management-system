package gui;

import org.kabeja.dxf.DXFDocument;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class inputFiles {
    public static DXFDocument doc;
    private Parser parser = ParserBuilder.createDefaultParser();
    private FileInputStream fileInputStream;
    public inputFiles() {
        JFileChooser jfilechooser = new JFileChooser();
        jfilechooser.setFileFilter(new FileNameExtensionFilter("*.dxf","DXF"));
        jfilechooser.showOpenDialog(MarcoPrincipal.principalPane);
        if(jfilechooser.getSelectedFile() == null) {
            JOptionPane.showMessageDialog(MarcoPrincipal.principalPane,"No file selected");
        }else {
            try {
                parser.parse(jfilechooser.getSelectedFile().getAbsolutePath());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            doc = parser.getDocument();
        }
            parser = null;
    }
}
