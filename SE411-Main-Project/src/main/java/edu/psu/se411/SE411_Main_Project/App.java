package edu.psu.se411.SE411_Main_Project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("SE411 Main Project");

        StackPane root = new StackPane(label);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("SE411 Main Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}