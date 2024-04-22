module com.example.conwaysgameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.conwaysgameoflife to javafx.fxml;
    exports com.example.conwaysgameoflife;
    exports com.example.conwaysgameoflife.ui to javafx.graphics;
    exports com.example.conwaysgameoflife.ui.components to javafx.graphics;

}