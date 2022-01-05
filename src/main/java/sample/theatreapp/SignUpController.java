package sample.theatreapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup, button_login;

    @FXML
    private TextField tf_username, tf_password, tf_fav_genre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(actionEvent, tf_username.getText(), tf_password.getText(), tf_fav_genre.getText());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "LogIn.fxml", "Log In!", null, null);
            }
        });
    }
}
