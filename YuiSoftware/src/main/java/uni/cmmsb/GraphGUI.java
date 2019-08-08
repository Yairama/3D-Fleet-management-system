package uni.cmmsb;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import org.fxyz3d.geometry.Point3D;
import org.fxyz3d.shapes.composites.PolyLine3D;
import org.kabeja.dxf.DXFLWPolyline;
import java.util.ArrayList;
import java.util.List;

public class GraphGUI {

    protected SubScene scene;

    protected Group sceneRoot;
    protected Text coordinates = new Text("Coordenadas");
    protected double xMouse;
    protected double yMouse;
    protected PerspectiveCamera camera;
    protected ArrayList<Point3D> ListCoord = new ArrayList<>();
    protected ArrayList<List<Point3D>> ListofList = new ArrayList<>();
    protected Rotate yRotate;
    protected Rotate zRotate;

    public GraphGUI(List<DXFLWPolyline> polylines, BorderPane parent) {
        baseGUI primaryBase = new baseGUI();
        for (int i=0; i<polylines.size();i++){

            for (int k=0; k<polylines.get(i).getVertexCount();k++) {
                double coordX = polylines.get(i).getVertex(k).getBounds().getMaximumX();
                double coordY = polylines.get(i).getVertex(k).getBounds().getMaximumY();
                double coordZ = polylines.get(i).getElevation();
                ListCoord.add(new Point3D((float)coordX,(float)coordY,(float)coordZ));
            }
            System.out.println("La cantidad de vertices es: "+polylines.get(i).getVertexCount());
            ListofList.add(new ArrayList<>(ListCoord));
            ListCoord.clear();
            //System.out.println(ListCoord.get(i));
        }
        System.out.println(ListofList.get(0));
        ListofList.get(0).get(0).add(new Point3D(0,0,0));
        System.out.println("La cantidad de polilineas es: "+polylines.size());

        final double sceneWidth = 600;
        final double sceneHeight = 600;
        double cameraDistance = 5000;


        sceneRoot = new Group();
        scene = new SubScene(sceneRoot, sceneWidth, sceneHeight, true, SceneAntialiasing.BALANCED);
        scene.widthProperty().bind(parent.widthProperty());
        scene.heightProperty().bind(parent.heightProperty());
        scene.setFill(Color.BLACK);
        camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(1000000.0);
        //camera.setRotate(180);
        //camera.setTranslateZ(000);
        //camera.setTranslateX((baseGUI.xMinimo+baseGUI.xMaximo)/2);
        //camera.setTranslateY((baseGUI.yMinimo+baseGUI.yMaximo)/2);


        scene.setCamera(camera);


        for (int i = 0; i<ListofList.size(); i++) {
            sceneRoot.getChildren().addAll(new PolyLine3D(ListofList.get(i), 5f, Color.WHITE));

        }





        scene.setOnScroll(event -> {
            System.out.println(event);
            if(event.getDeltaY()>0){
            camera.translateZProperty().set(camera.getTranslateZ()-100);}
            else if(event.getDeltaY()<0){camera.translateZProperty().set(camera.getTranslateZ()+100);}
        });

        Translate pivot = new Translate((baseGUI.xMinimo+baseGUI.xMaximo)/2,(baseGUI.yMinimo+baseGUI.yMaximo)/2,3787);
        yRotate = new Rotate(180, Rotate.Y_AXIS);
        zRotate = new Rotate(180,Rotate.Z_AXIS);
        Translate pivot2 = new Translate();
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(0, Rotate.X_AXIS),
                new Translate(0,0,-3000)
        );
        camera.getTransforms().addAll(pivot2,zRotate);
        System.out.println(camera.getTransforms());
        sceneRoot.getChildren().add(camera);


        parent.setCenter(scene);
    }

    public void eventKeys(KeyEvent event){

            System.out.println(event.getCode());
            double change = 1000.0;
            //Add shift modifier to simulate "Running Speed"
            if(event.isShiftDown()) { change = 5000.0; }
            //What key did the user press?
            KeyCode keycode = event.getCode();
            //Step 2c: Add Zoom controls
            switch (event.getCode()) {
                case W:
                    camera.translateYProperty().set(camera.getTranslateY() + 100);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY() - 100);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() - 100);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() + 100);
                    break;
                case Q:
                    yRotate.angleProperty().set(yRotate.getAngle()+10);
                    System.out.println(yRotate.getAngle());
                    break;
                case E:
                    yRotate.angleProperty().set(yRotate.getAngle()-10);
                    System.out.println(yRotate.getAngle());
                    break;
                case Z:
                    zRotate.angleProperty().set(zRotate.getAngle()+10);
                    break;
                case C:
                    zRotate.angleProperty().set(zRotate.getAngle()-10);
            }
    }

}
