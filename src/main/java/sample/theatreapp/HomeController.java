package sample.theatreapp;

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

        button_logout.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent, "LogIn.fxml", "Log In!", null, null));
    }

    public void setUserInformation (String firstName, String favGenre) {
        label_first_name.setText((firstName));
        label_fav_genre.setText((favGenre));
    }
}
