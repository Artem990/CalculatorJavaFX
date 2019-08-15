package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Calculator");
    primaryStage.setScene(new Scene(root, 513, 340));
    primaryStage.setResizable(false);
    primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Driver.closeConnections();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
