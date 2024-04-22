module com.example.conwaysgameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.conwaysgameoflife to javafx.fxml;
    exports com.example.conwaysgameoflife;
}