package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainMenu.fxml"));
        primaryStage.setTitle("Система мониторинга моточасов");
        Scene scene = new Scene(root, 1005, 614);
        scene.getStylesheets().add(0,"css/main.css");
        primaryStage.getIcons().add(new Image("/image/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
