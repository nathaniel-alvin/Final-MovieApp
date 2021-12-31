package sample.theatreapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

/*
    Helper class
 */
public class DBUtils {

     public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String favGenre) {
         Parent root = null;
         
         if (username != null && favGenre != null) {
             try {
                 FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                 root = loader.load();
                 HomeController homeController = loader.getController();
                 homeController.setUserInformation(username, favGenre);
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

     public static void signUpUser(ActionEvent event, String username, String password, String favGenre) {
         Connection connection = null;
         PreparedStatement psInsert = null;
         PreparedStatement psCheckUserExists = null;
         ResultSet resultSet = null;
         try {
             connection = DriverManager.getConnection("jdbc:mysql://103.82.242.16:5555/nathaniel_alvin", "nathaniel-alvin", "42430");
             psCheckUserExists = connection.prepareStatement("SELECT * FROM Accounts WHERE username = ?");
             psCheckUserExists.setString(1, username);
             resultSet = psCheckUserExists.executeQuery();

             // check if resultSet is empty; return true if exist (username taken)
             if (resultSet.isBeforeFirst()) {
                 System.out.println("User already exist!");
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("You cannot use this username. ");
                 alert.show();
             } else {
                 // update username, password, fav_genre

                 psInsert = connection.prepareStatement("INSERT INTO Accounts (username, passoword, tickets, fav_genre) VALUES (?, ?, null, ?) ");
                 psInsert.setString(1, username);
                 psInsert.setString(2, password);
                 psInsert.setString(3, favGenre);
                 psInsert.executeUpdate();

                 // change scene
                 changeScene(event, "Home.fxml", "Welcome!", username, favGenre);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             if (resultSet != null) {
                 try {
                     resultSet.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (psCheckUserExists != null) {
                 try {
                     psCheckUserExists.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (psInsert != null) {
                 try {
                     psInsert.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (connection != null) {
                 try {
                     connection.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
     public static void logInUser(ActionEvent event, String username, String password) {
         Connection connection = null;
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;

         try {
             connection = DriverManager.getConnection("jdbc:mysql://103.82.242.16:5555/nathaniel_alvin", "nathaniel-alvin", "42430");
             preparedStatement = connection.prepareStatement("SELECT password, fav_genre FROM Accounts WHERE username = ?");
             preparedStatement.setString(1, username);
             resultSet = preparedStatement.executeQuery();

             if (!resultSet.isBeforeFirst()) {
                 System.out.println("User not found in the database!");
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setContentText("Provided credentials are incorrect!");
                 alert.show();
             } else {
                 while (resultSet.next()) {
                     String retrievedPassword = resultSet.getString("password");
                     String retrievedGenre = resultSet.getString("fav_genre");
                     if (retrievedPassword.equals(password)) {
                         changeScene(event, "Home.fxml", "Welcome!", username, retrievedGenre);
                     } else {
                         System.out.println("Password did not match!");
                         Alert alert = new Alert(Alert.AlertType.ERROR);
                         alert.setContentText("The provided credentials are incorrect!");
                         alert.show();
                     }
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             if (resultSet != null) {
                 try {
                     resultSet.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (preparedStatement != null) {
                 try {
                     preparedStatement.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (connection != null) {
                 try {
                     connection.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }

     }
}
