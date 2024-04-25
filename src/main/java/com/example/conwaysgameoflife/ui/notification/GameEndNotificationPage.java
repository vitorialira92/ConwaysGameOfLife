package com.example.conwaysgameoflife.ui.notification;

import com.example.conwaysgameoflife.ui.ConfigurationPage;
import com.example.conwaysgameoflife.ui.components.StandardComponents;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GameEndNotificationPage extends Application {
    private VBox layout;
    @Override
    public void start(Stage stage) throws Exception {
        loadScreen(stage);
    }

    private void loadScreen(Stage stage){
        Label label = StandardComponents.getLabel("All cells are dead.\nDo you want to star over?");
        Button startOverButton = StandardComponents.getButton("START OVER");
        startOverButton.setOnAction(e -> {
            closeAllWindows();
            openConfigurationsPage();
            ((Stage) layout.getScene().getWindow()).close();
        });

        Button closeButton = StandardComponents.getButton("CLOSE");
        closeButton.setOnAction(e -> {
            Platform.exit();
        });

        layout = new VBox(40, label, startOverButton, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #151515; -fx-padding: 20;");

        Scene scene = new Scene(layout, 755, 383);
        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();
    }

    private void closeAllWindows(){
        for (Window window : Stage.getWindows()) {
            if(window instanceof Stage)
                ((Stage) window).close();
        }
    }

    private void openConfigurationsPage(){
        ConfigurationPage configurationPage = new ConfigurationPage();
        Stage stage = new Stage();
        try {
            configurationPage.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
