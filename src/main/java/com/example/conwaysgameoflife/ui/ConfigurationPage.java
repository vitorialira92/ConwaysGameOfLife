package com.example.conwaysgameoflife.ui;

import com.example.conwaysgameoflife.configuration.Configurations;
import com.example.conwaysgameoflife.ui.components.StandardComponents;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class ConfigurationPage extends Application {

    private static Label titleLabel;
    private static VBox rowsTextField;
    private static VBox columnsTextField;
    private static VBox livingCellsTextField;
    private static Button startButton;
    private static VBox layout;

    @Override
    public void start(Stage stage){
        loadScreen(stage);
    }

    private void loadScreen(Stage stage){
        loadComponents();
        Scene scene = new Scene(layout, 1440, 700);
        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();
    }

    private void loadComponents() {
        titleLabel = StandardComponents.getTitle("Conway's Game of Life");

        startButton = StandardComponents.getButton("START");
        startButton.setOnAction(this::startButtonClick);

        rowsTextField = StandardComponents.getOnlyNumbersTextField(
                "Rows",
                    String.valueOf(Configurations.MIN_ROWS),
                    String.valueOf(Configurations.MAX_ROWS));
        columnsTextField = StandardComponents.getOnlyNumbersTextField(
                "Columns",
                    String.valueOf(Configurations.MIN_COLUMNS),
                    String.valueOf(Configurations.MAX_COLUMNS));
        livingCellsTextField = StandardComponents.getOnlyNumbersTextField(
                "Living cells\n*If you don't enter a number, it will be \nrandom",
                    String.valueOf(Configurations.MIN_INITIAL_LIVE_CELLS),
                    "rows * columns - 3");
        livingCellsTextField.setMaxWidth(300);

        HBox sizes = new HBox(64, rowsTextField, columnsTextField);
        sizes.setAlignment(Pos.CENTER);

        layout = new VBox(64, titleLabel, sizes, livingCellsTextField, startButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #151515; -fx-padding: 20;");
    }

    private int getTextFieldNumericInput(VBox numericTextField, int min){
        int numericInput = 0;

        for (javafx.scene.Node child : numericTextField.getChildren()) {
            if (child instanceof TextField textField) {
                if(textField.getText().isEmpty())
                    numericInput = min;
                else{
                    int value = Integer.parseInt(textField.getText());
                    numericInput = Math.max(value, min);
                }
            }
        }

        return numericInput;
    }
    private void startButtonClick(ActionEvent actionEvent) {
        int row = getTextFieldNumericInput(rowsTextField, Configurations.MIN_ROWS);
        int column = getTextFieldNumericInput(columnsTextField, Configurations.MIN_COLUMNS);
        int initialLiveCells = 0;

        boolean getRandomLivingCells = false;

        for (javafx.scene.Node child : livingCellsTextField.getChildren()) {
            if (child instanceof TextField textField) {
                if(textField.getText().isEmpty())
                    getRandomLivingCells = true;
                else{
                    int value = Integer.parseInt(textField.getText());
                    initialLiveCells = Math.max(value, Configurations.MIN_INITIAL_LIVE_CELLS);
                    if(initialLiveCells >= (row * column) - 2){
                        initialLiveCells = row * column - 3;
                        textField.setText(String.valueOf(initialLiveCells));
                    }
                }
            }
        }
        //setar novos valores
        Configurations.rows = Math.min(row, Configurations.MAX_ROWS);
        Configurations.columns = Math.min(column, Configurations.MAX_COLUMNS);

        if(getRandomLivingCells)
            initialLiveCells = new Random()
                    .nextInt(Configurations.MIN_INITIAL_LIVE_CELLS,
                            Configurations.rows * Configurations.columns);

        ((Stage) titleLabel.getScene().getWindow()).close();

        GamePage gamePage = new GamePage(Math.min(initialLiveCells, Configurations.MAX_INITIAL_LIVE_CELLS));
        Stage stage = new Stage();
        try {
            gamePage.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
