package FirstPack;


import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import org.kabeja.dxf.*;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;
import org.kabeja.svg.ui.SVGViewUIComponent;
import org.kabeja.ui.UIException;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class baseGUI implements Initializable {
    /**Constructor*/
    public baseGUI(){
    }

    /**Atribtuos*/

    protected DXFDocument doc=new DXFDocument();
    protected SVGViewUIComponent DXFViewer=new SVGViewUIComponent();
    protected File saveFile;
    final GLProfile profile = GLProfile.get(GLProfile.GL2);
    GLCapabilities capabilities = new GLCapabilities(profile);
    protected GLCanvas glCanvas = new GLCanvas(capabilities);


    @FXML
    private SwingNode swingNode=new SwingNode();
    @FXML
    private SwingNode swingNodegl =new SwingNode();
    @FXML
    private ComboBox combo1=new ComboBox();

     /**Metodos*/
    @FXML
    private void openFile(ActionEvent event){
        FileChooser selectFile = new FileChooser();
        selectFile.setTitle("Please select the DXF file to use");
        selectFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("DXF","*.dxf"));
        saveFile = selectFile.showOpenDialog(null);
        if(saveFile==null){
            return;
        }

        DXFReader(saveFile);

    }
    private void DXFReader(File path) {
        Parser parser = ParserBuilder.createDefaultParser();
        DXFViewer = new SVGViewUIComponent();

        glCanvas.setSize(1000,1000);
        glCanvas.addGLEventListener(new GLEventListener() {
            @Override
            public void init(GLAutoDrawable glAutoDrawable) {
            }

            @Override
            public void dispose(GLAutoDrawable glAutoDrawable) {
            }

            @Override
            public void display(GLAutoDrawable glAutoDrawable) {
            }

            @Override
            public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
            }
        });

        GLJPanel gljPanel = new GLJPanel();
        gljPanel.add(glCanvas);
        //gljPanel.setBackground(Color.blue);
        gljPanel.setSize(500,500);


        try {
            parser.parse(path.getAbsolutePath(), DXFParser.DEFAULT_ENCODING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doc = parser.getDocument();


        Iterator<DXFLayer> layerIt = doc.getDXFLayerIterator();
        while(layerIt.hasNext())
        {
            combo1.getItems().add(layerIt.next().getName());
        }
        DXFLayer LayerOne = doc.getDXFLayer("CN_Z1_3107");
        System.out.println(LayerOne.hasDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE));
        List<DXFLWPolyline> PoliList = LayerOne.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE);
        System.out.println(PoliList);
        System.out.println(PoliList.get(0).getVertex(1).getBounds().getMaximumX());
        System.out.println(PoliList.get(0).getVertex(1).getBounds().getMinimumX());
        System.out.println(PoliList.get(0).getVertex(1).getBounds().getMinimumY());
        System.out.println(PoliList.get(0).getElevation());
        getCoordenadas(PoliList);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(DXFViewer.getView());
                //gljPanel.add(new JButton("Hablamos"));
                //gljPanel.add(glCanvas);
                swingNodegl.setContent(gljPanel);
                try {
                    DXFViewer.showDXFDocument(doc);
                } catch (UIException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected List<double[]> ListCoord = new ArrayList<double[]>();
    private void getCoordenadas(List<DXFLWPolyline> polylines){
        double[] Policoordenadas = new double[3];
        for (int i=0; i<polylines.size();i++){
            for (int k=0; k<polylines.get(i).getVertexCount();k++) {
                double coordX = polylines.get(i).getVertex(k).getBounds().getMaximumX();
                double coordY = polylines.get(i).getVertex(k).getBounds().getMaximumY();
                double coordZ = polylines.get(i).getElevation();
                Policoordenadas[0]=coordX;
                Policoordenadas[1]=coordY;
                Policoordenadas[2]=coordZ;
            }
            ListCoord.add(Policoordenadas);
            System.out.println(ListCoord.get(i)[0]+"-"+ListCoord.get(i)[1]+"-"+ListCoord.get(i)[2]);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(DXFViewer.getView());
            }
        });
    }

}

