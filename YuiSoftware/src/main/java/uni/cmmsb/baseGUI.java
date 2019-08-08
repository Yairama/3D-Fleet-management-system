package uni.cmmsb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxyz3d.geometry.Point3D;
import org.fxyz3d.shapes.composites.PolyLine3D;
import org.kabeja.dxf.*;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class baseGUI implements Initializable {
    /**Constructor*/
    public baseGUI(){
    }

    /**Atribtuos*/

    protected DXFDocument doc = new DXFDocument();
    protected File saveFile;
    protected static double xMinimo;
    protected static double xMaximo;
    protected static double yMinimo;
    protected static double yMaximo;
    protected static double zMinimo;
    protected static double zMaximo;

    @FXML
    protected Tab tbnDXFView = new Tab();
    @FXML
    protected ComboBox<String> cmbLayouts = new ComboBox<>();
    @FXML
    protected BorderPane bpnMain =  new BorderPane();
    /**Metodos*/

    @FXML
    private void openFile(ActionEvent event){
        saveFile = null;
        FileChooser selectFile = new FileChooser();
        selectFile.setTitle("Porfavor elija el DXF");
        selectFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("DXF","*.dxf"));
        saveFile = selectFile.showOpenDialog(null);
        if(saveFile==null){
            return;
        }
        DXFReader(saveFile);
    }

    private void DXFReader(File path) {
        doc = null;
        cmbLayouts.getItems().clear();
        Parser parser = ParserBuilder.createDefaultParser();
        try {
            parser.parse(path.getAbsolutePath(), DXFParser.DEFAULT_ENCODING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doc = parser.getDocument();
        Iterator<DXFLayer> layerIt = doc.getDXFLayerIterator();
        while (layerIt.hasNext()){
            cmbLayouts.getItems().add(layerIt.next().getName());
        }
        xMinimo = doc.getBounds().getMinimumX();
        xMaximo = doc.getBounds().getMaximumX();
        yMinimo = doc.getBounds().getMinimumY();
        yMaximo = doc.getBounds().getMaximumY();
        zMinimo = doc.getBounds().getMinimumZ();
        zMaximo = doc.getBounds().getMaximumZ();

    }

    @FXML
    private void btnGraficar(){
        System.out.println(cmbLayouts.getValue());
        DXFLayer LayerOne = doc.getDXFLayer(cmbLayouts.getValue());
        System.out.println(LayerOne.hasDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE));
        List<DXFLWPolyline> PoliList = LayerOne.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE);
        System.out.println(PoliList);
        GraphGUI DXFGraficador = new GraphGUI(PoliList, bpnMain);

        bpnMain.getScene().setOnKeyPressed(event -> {
            DXFGraficador.eventKeys(event);
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


}

