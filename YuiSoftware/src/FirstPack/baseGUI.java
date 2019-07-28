package FirstPack;

import com.sun.javafx.scene.paint.GradientUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.batik.swing.JSVGCanvas;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class baseGUI implements Initializable {
    public baseGUI(){   }

    private File saveFile;
    private SwingNode SVGViewer=new SwingNode();
    private JSVGCanvas SVGCanvas = new JSVGCanvas();
    public void swingContenteSVG(SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(SVGCanvas);
            }
        });
    }

    @FXML
    private AnchorPane anchDXF=new AnchorPane();

    @FXML
    private void openFile(ActionEvent event){
        FileChooser selectFile = new FileChooser();
        selectFile.setTitle("Please select the DXF file to use");
        selectFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("DXF","*.dxf"));
        saveFile = selectFile.showOpenDialog(null);
        DXFReader(saveFile.getAbsolutePath(),"PARCELA");
        swingContenteSVG(SVGViewer);
        anchDXF.getChildren().add(SVGViewer);
    }

    public  void DXFReader(String paht, String Layerid){

        Parser parser = ParserBuilder.createDefaultParser();
        try {
            parser.parse(paht,DXFParser.DEFAULT_ENCODING);
            DXFDocument doc = parser.getDocument();
            DXFLayer Parcela = doc.getDXFLayer(Layerid);
            List polyLines = Parcela.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE);

        }catch (ParseException e ){}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
