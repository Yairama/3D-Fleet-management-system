package uni.cmmsb;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.fxyz3d.geometry.Point3D;
import org.fxyz3d.shapes.composites.PolyLine3D;
import org.kabeja.dxf.*;
import org.kabeja.dxf.objects.DXFLayout;
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
    protected List<DXFLayer> layerList = new ArrayList<>();
    protected List<Point3D> point3DFinalList = new ArrayList<>();
    @FXML
    protected Tab tbnDXFView = new Tab();
    @FXML
    protected CheckComboBox<String> chmbLayouts = new CheckComboBox<>();
    @FXML
    protected CheckComboBox<String> chmbEntidades = new CheckComboBox<>();
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

        chmbLayouts.getItems().clear();
        Parser parser = ParserBuilder.createDefaultParser();
        try {
            parser.parse(path.getAbsolutePath(), DXFParser.DEFAULT_ENCODING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doc = parser.getDocument();
        Iterator<DXFLayer> layerIt = doc.getDXFLayerIterator();
        while (layerIt.hasNext()){
            chmbLayouts.getItems().add(layerIt.next().getName());
        }



        chmbLayouts.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                layerList.clear();
                for(int i =0; i<chmbLayouts.getCheckModel().getCheckedIndices().size();i++){
                    layerList.add(doc.getDXFLayer(chmbLayouts.getCheckModel().getItem(
                            chmbLayouts.getCheckModel().getCheckedIndices().get(i))));
                }
                System.out.println(layerList);
            }
        });

        chmbEntidades.getItems().addAll( "Polilineas","LWPolilineas","Puntos");

        xMinimo = doc.getBounds().getMinimumX();
        xMaximo = doc.getBounds().getMaximumX();
        yMinimo = doc.getBounds().getMinimumY();
        yMaximo = doc.getBounds().getMaximumY();
        zMinimo = doc.getBounds().getMinimumZ();
        zMaximo = doc.getBounds().getMaximumZ();

    }



    @FXML
    private void btnGraficarDibujo(){
        GraphGUI DXFGraficador = new GraphGUI(bpnMain);

        bpnMain.getScene().setOnKeyPressed(event -> {
            DXFGraficador.eventKeys(event);
        });

        List<String> checkedItems = chmbEntidades.getCheckModel().getCheckedItems();
        for (String checkedItem : checkedItems){
            switch (checkedItem){
                case "Polilineas":
                    polylineGetter(layerList);
                    break;
                case "LWPolilineas":
                    lwpolylineGetter(layerList);
                    break;
                case "Puntos":
                    pointsGetter(layerList);
                    break;
            }
        }


    }

    private List<Point3D> polylineGetter(List<DXFLayer> layersList){
        List<DXFPolyline> polylines = new ArrayList<>();
        List<Point3D> ListCoord = new ArrayList<>();
        List<List<Point3D>> ListofList = new ArrayList<>();
        for(DXFLayer layer : layersList){
            if(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE)!=null)
            {polylines.addAll(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE));}

        }

        for (int i=0; i<polylines.size();i++){

            for (int k=0; k<polylines.get(i).getVertexCount();k++) {
                double coordX = polylines.get(i).getVertex(k).getBounds().getMaximumX();
                double coordY = polylines.get(i).getVertex(k).getBounds().getMaximumY();
                double coordZ = polylines.get(i).getVertex(k).getBounds().getMaximumZ();
                ListCoord.add(new Point3D((float)coordX,(float)coordY,(float)coordZ));
            }
        }
        return ListCoord;
    }

    private List<Point3D> lwpolylineGetter(List<DXFLayer> layersList){
        List<DXFLWPolyline> polylines = new ArrayList<>();
        List<Point3D> ListCoord = new ArrayList<>();
        List<List<Point3D>> ListofList = new ArrayList<>();
        for(DXFLayer layer : layersList){
            if(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE)!=null)
            {polylines.addAll(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE));}

        }

        for (int i=0; i<polylines.size();i++){

            for (int k=0; k<polylines.get(i).getVertexCount();k++) {
                double coordX = polylines.get(i).getVertex(k).getBounds().getMaximumX();
                double coordY = polylines.get(i).getVertex(k).getBounds().getMaximumY();
                double coordZ = polylines.get(i).getElevation();
                ListCoord.add(new Point3D((float)coordX,(float)coordY,(float)coordZ));
            }
        }
        return ListCoord;
    }

    private List<Point3D> pointsGetter(List<DXFLayer> layersList){
        List<Point3D> LisCoord = new ArrayList<>();
        for(DXFLayer layer : layersList){
            if(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POINT)!=null)
            {LisCoord.addAll(layer.getDXFEntities(DXFConstants.ENTITY_TYPE_POINT));}

        }
        return LisCoord;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


}

