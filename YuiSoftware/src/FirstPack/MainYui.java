package FirstPack;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainYui extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    baseGUI sadasd=new baseGUI();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("baseGUI.fxml"));
        stage.setTitle("Yui Software");
        stage.setScene(new Scene(root,1200,800));
        stage.show();
    }
}
