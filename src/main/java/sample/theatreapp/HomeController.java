package sample.theatreapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button button_logout, button_edit, button_view_films, button_view_bookings;

    @FXML
    private Label label_first_name, label_last_name, label_fav_genre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

    }

    public void setUserInformation (String firstName, String lastName, String favGenre) {
        label_first_name.setText((firstName));
        label_last_name.setText((lastName));
        label_fav_genre.setText((favGenre));
    }
}
