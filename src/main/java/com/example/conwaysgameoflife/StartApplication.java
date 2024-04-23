package com.example.conwaysgameoflife;

import com.example.conwaysgameoflife.ui.ConfigurationPage;
import com.example.conwaysgameoflife.ui.components.StandardComponents;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox layout = new VBox(10, StandardComponents.getTitle("Conway's Game Of Life"));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #151515; -fx-padding: 20;");

        Scene scene = new Scene(layout, 1440, 700);

        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();

        PauseTransition delay = new PauseTransition(Duration.millis(2500));
        delay.setOnFinished(event -> {
            ((Stage) layout.getScene().getWindow()).close();

            ConfigurationPage configPage = new ConfigurationPage();
            Stage newStage = new Stage();
            try {
                configPage.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    public static void main(String[] args) {
        launch();
    }
}