module sample.theatreapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.theatreapp to javafx.fxml;
    exports sample.theatreapp;
}