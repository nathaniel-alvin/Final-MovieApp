package sample.theatreapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
    Helper class
 */
public class DBUtils {

     public static void changeScene(ActionEvent event, String fxmlFile, String title, String firstName, String lastName, String favGenre) {
         Parent root = null;
         
         if (firstName != null && lastName != null && favGenre != null) {
             try {
                 FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                 root = loader.load();
                 HomeController homeController = loader.getController();
                 homeController.setUserInformation(firstName, lastName, favGenre);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         } else {
             try {
                 root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setTitle(title);
         stage.setScene(new Scene(root, 600, 400));
         stage.show();
     }
}
