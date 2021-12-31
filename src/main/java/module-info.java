module sample.theatreapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sample.theatreapp to javafx.fxml;
    exports sample.theatreapp;
}