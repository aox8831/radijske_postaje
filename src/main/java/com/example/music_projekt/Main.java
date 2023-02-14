package com.example.music_projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Evidenca radijskih postaj");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void changeScene(String fxml, Integer sirina, Integer visina) throws IOException {
        Parent pane = FXMLLoader.load(Main.class.getResource(fxml));
        stg.setScene(new Scene(pane,sirina,visina));
    }

    public static void main(String[] args) {

        launch(args);
    }
}


