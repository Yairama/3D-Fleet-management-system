package FirstPack;

import com.sun.javafx.scene.paint.GradientUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.batik.swing.JSVGCanvas;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFEntity;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.kabeja.svg.ui.SVGViewUIComponent;
import org.kabeja.ui.UIException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class baseGUI implements Initializable {
    public baseGUI(){   }

    private File saveFile;
    @FXML
    private SwingNode swingNode=new SwingNode();
    private DXFDocument doc=new DXFDocument();
    @FXML
    private AnchorPane anchDXF=new AnchorPane();

    @FXML
    private void openFile(ActionEvent event){
        FileChooser selectFile = new FileChooser();
        selectFile.setTitle("Please select the DXF file to use");
        selectFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("DXF","*.dxf"));
        saveFile = selectFile.showOpenDialog(null);
        DXFReader(saveFile);
    }
    // hola
    private void DXFReader(File path){
        Parser parser = ParserBuilder.createDefaultParser();
        SVGViewUIComponent DXFViewer=new SVGViewUIComponent();
        try {
            parser.parse(path.getAbsolutePath(),DXFParser.DEFAULT_ENCODING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doc = parser.getDocument();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                swingNode.setContent(DXFViewer.getView());
                try {
                    DXFViewer.showDXFDocument(doc);
                } catch (UIException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
