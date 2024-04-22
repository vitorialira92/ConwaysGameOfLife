package com.example.conwaysgameoflife.ui;

import com.example.conwaysgameoflife.configuration.Configurations;
import com.example.conwaysgameoflife.services.GameOfLiveService;
import com.example.conwaysgameoflife.ui.components.StandardComponents;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GamePage extends Application {
    private double timeToReloadGame;
    private int[][] state;
    private static GridPane gameGrid;
    private static Label title;
    private static Label gridSize;
    private static Label liveAndDeadCounter;
    private static Label timeCounter;
    private static Label generationsCounter;
    private static Button newButton;
    private static Button generationsPerMinuteChangeButton;
    private static VBox generationsPerMinuteTextField;

    private static int generations;
    private static Instant begin;

    private static int totalCells;
    private static int liveCells;

    public GamePage(int initialLivingCells){
        gameGrid = new GridPane();
        timeToReloadGame = (double) 60000 / Configurations.generationsPerMinute;
        totalCells = Configurations.rows * Configurations.columns;
        liveCells = initialLivingCells;
        LoadInitialGameGrid(initialLivingCells);
        generations = 1;
    }

    private void LoadInitialGameGrid(int initialLivingCells) {
        int rows = Configurations.rows;
        int columns = Configurations.columns;

        initialLivingCells = new Random().nextInt(5, rows*columns);

        double w = (double) 780 / columns;
        double h = (double) 560 / rows;

        state = new int[rows][columns];

        List<Point2D> allCoordinates = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                allCoordinates.add(new Point2D(i, j));
                state[i][j] = 0;
            }
        }

        Collections.shuffle(allCoordinates, new Random());

        allCoordinates = allCoordinates.subList(0, initialLivingCells);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                Rectangle rect = new Rectangle(w, h);

                rect.setStroke(Color.BLACK);
                rect.setFill(Color.WHITE);

                if(allCoordinates.contains(new Point2D(i, j))){
                    rect.setFill(Color.GREEN);
                    state[i][j] = 1;
                }

                gameGrid.add(rect, j, i);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        begin = Instant.now();
        loadScreen(stage);
    }

    private void loadScreen(Stage stage) {

        title = StandardComponents.getLabel("Conway's Game of Life");
        gridSize = StandardComponents.getLabel(Configurations.rows +
                " rows x " + Configurations.columns + " columns");
        liveAndDeadCounter = StandardComponents.getLabel( totalCells + " total cells\n" +
                "----> " + liveCells + " live | " + (totalCells - liveCells) + " dead");
        timeCounter = StandardComponents.getLabel("00:00:00 passed");
        generationsCounter = StandardComponents.getLabel(" | " + generations + " generations");

        HBox oldSubtitle = new HBox(12, StandardComponents.getSmallRectangle(Color.YELLOW),
                StandardComponents.getLabel("OLD"));

        HBox newSubtitle = new HBox(12, StandardComponents.getSmallRectangle(Color.GREEN),
                StandardComponents.getLabel("NEW"));

        generationsPerMinuteTextField = StandardComponents.getOnlyNumbersTextField(
                "Generations per minute",
                Configurations.MIN_GENERATIONS_PER_MINUTE, Configurations.MAX_GENERATIONS_PER_MINUTE);
        generationsPerMinuteChangeButton = StandardComponents.getButton("APPLY");

        newButton = StandardComponents.getButton("START OVER");
        VBox leftColumn = new VBox(20, title, gridSize, liveAndDeadCounter,
                StandardComponents.getDivider(), oldSubtitle, newSubtitle, StandardComponents.getDivider(),
                generationsPerMinuteTextField, generationsPerMinuteChangeButton,
                StandardComponents.getDivider(), newButton);
        leftColumn.setAlignment(Pos.CENTER);

        HBox timeAndGenerationsHBox = new HBox(0, timeCounter, generationsCounter);
        timeAndGenerationsHBox.setAlignment(Pos.CENTER);

        VBox rightColumn = new VBox(20, gameGrid, timeAndGenerationsHBox);
        rightColumn.setAlignment(Pos.CENTER);

        HBox layout = new HBox(64, leftColumn, rightColumn);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #151515; -fx-padding: 20;");

        Line vLine = new Line(150, 0, 150, 100); // x de início, y de início, x de fim, y de fim
        vLine.setStrokeWidth(2);

        Scene scene = new Scene(layout, 1440, 700);
        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();

        loadUIUpdate();
    }

    private void loadUIUpdate() {
        //grid
        Duration duration = Duration.millis(timeToReloadGame);

        KeyFrame keyFrame = new KeyFrame(duration, event -> LoadGameGrid());

        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

        //time counter

        KeyFrame clockKeyFrame =
                new KeyFrame(Duration.seconds(1), e -> {
            long seconds = ChronoUnit.SECONDS.between(begin, Instant.now());
            timeCounter.setText(String.format("%02d:%02d:%02d",
                    seconds / 3600, (seconds % 3600) / 60, seconds % 60) + " passed");

        });

        Timeline clockTimeline = new Timeline(clockKeyFrame);
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();

        //generations

        KeyFrame generationsKeyFrame =
                new KeyFrame(Duration.millis(1), e -> {
                    generationsCounter.setText(" | " + generations + " generations");
                });

        Timeline generationsTimeline = new Timeline(generationsKeyFrame);
        generationsTimeline.setCycleCount(Timeline.INDEFINITE);
        generationsTimeline.play();

        //live and dead

        KeyFrame liveAndDeadKeyFrame =
                new KeyFrame(Duration.millis(1), e -> {
                    liveAndDeadCounter.setText(totalCells + " total cells\n" +
                            "----> " + liveCells + " live | " + (totalCells - liveCells) + " dead");
                });

        Timeline liveAndDeadTimeline = new Timeline(liveAndDeadKeyFrame);
        liveAndDeadTimeline.setCycleCount(Timeline.INDEFINITE);
        liveAndDeadTimeline.play();
    }

    private void LoadGameGrid(){
        int[][] newState = GameOfLiveService.Play(this.state);
        updateGridWithNewState(newState);
    }

    private void updateGridWithNewState(int[][] newState) {

        generations++;
        int count = 0;

        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle rect = (Rectangle) node;

                Integer colIndex = GridPane.getColumnIndex(node);
                Integer rowIndex = GridPane.getRowIndex(node);

                if (colIndex != null && rowIndex != null) {
                    if (newState[rowIndex][colIndex] == 0) {
                        rect.setFill(Color.WHITE);
                    } else if(state[rowIndex][colIndex] == 0){
                        count ++;
                        rect.setFill(Color.GREEN);
                    }
                    else{
                        count ++;
                        rect.setFill(Color.YELLOW);
                    }

                }
            }
        }
        liveCells = count;

        this.state = newState;
    }
}
