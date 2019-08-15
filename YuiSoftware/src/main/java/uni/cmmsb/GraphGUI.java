package uni.cmmsb;

import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class GraphGUI {

    protected SubScene scene;
    protected Group sceneRoot;
    protected PerspectiveCamera camera;
    protected Rotate yRotate;
    protected Rotate zRotate;

    public GraphGUI(BorderPane parent) {
        final double sceneWidth = 600;
        final double sceneHeight = 600;
        sceneRoot = new Group();
        scene = new SubScene(sceneRoot, sceneWidth, sceneHeight, true, SceneAntialiasing.BALANCED);
        scene.widthProperty().bind(parent.widthProperty());
        scene.heightProperty().bind(parent.heightProperty());
        scene.setFill(Color.BLACK);
        camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(1000000.0);
     scene.setCamera(camera);
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
