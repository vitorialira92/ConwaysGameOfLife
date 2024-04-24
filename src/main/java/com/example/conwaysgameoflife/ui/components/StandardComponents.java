package com.example.conwaysgameoflife.ui.components;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class StandardComponents {
    public static Button getButton(String text){
        Button button = new Button(text);
        button.setStyle("-fx-padding: 12px 48px; " +
                "-fx-background-color: #151515; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 24px; " +
                "-fx-justify-content: center; " +
                "-fx-align-items: center; " +
                "-fx-border-radius: 32px; " +
                "-fx-border-width: 3px; " +
                "-fx-border-color: white;");
        button.setCursor(Cursor.HAND);
        return button;
    }

    public static VBox getOnlyNumbersTextField(String text, String min, String max){
        VBox vbox = getTextField(text + "\n(min: " + min + ", max: " + max + ")");

        TextField tf = (TextField)vbox.getChildren().get(1);
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                tf.setText(newValue.replaceAll("[^\\d]", ""));


            if (!tf.getText().isEmpty()) {
                try {
                    int value = Integer.parseInt(tf.getText());
                    if (value < 0)
                        tf.setText("");
                } catch (NumberFormatException e) {
                    tf.clear();
                }
            }
        });

        return vbox;
    }

    public static VBox getTextField(String text){
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        TextField textField = new TextField();
        textField.setStyle("-fx-text-fill: white; " +
                "-fx-padding: 12px 12px; " +
                "-fx-background-color: #151515; " +
                "-fx-border-color: white; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 16px; " +
                "-fx-background-radius: 16px;");

        VBox layout = new VBox(8, label, textField);
        layout.setAlignment(Pos.TOP_LEFT);

        return layout;
    }

    public static Label getTitle(String text){
        Label titleLabel = new Label(text);
        titleLabel.setStyle("-fx-font-size: 64px; -fx-text-fill: white;");

        return titleLabel;
    }

    public static Label getLabel(String text){
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");

        return label;
    }

    public static Line getDivider(){
        Line line = new Line(0, 0, 300, 0);
        line.setStrokeWidth(2);
        line.setStroke(Color.WHITE);

        return line;
    }

    public static Rectangle getSmallRectangle(Paint color){
        Rectangle r = new Rectangle(30, 30);
        r.setStroke(Color.BLACK);
        r.setFill(color);

        return r;
    }
}
