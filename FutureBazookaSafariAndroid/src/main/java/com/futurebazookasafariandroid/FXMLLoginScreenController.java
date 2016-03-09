package com.futurebazookasafariandroid;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLLoginScreenController {

    @FXML
    private TextField fbs_username;
    @FXML
    private PasswordField fbs_passwort;
    @FXML
    private GridPane gridlogin;

    @FXML
    private void LoginPressed(ActionEvent event) throws IOException, SQLException {

        try {
            if (FutureBazookaSafariAndroid.datenbank.login(fbs_username.getText(), fbs_passwort.getText())) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));

              

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setScene(new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
                stage.setX(0);
                stage.setY(0);
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }

    }
    
    
    
    
     @FXML
    public void initialize() {   
          String image = FXMLLoginScreenController.class.getResource("logo_nue.png").toExternalForm();
                gridlogin.setStyle("-fx-background-image: url('" + image + "'); "
                        + "-fx-background-position: center center; "
                        + "-fx-background-repeat: stretch;");

    }

    @FXML
    private void SignUpPressed(ActionEvent event) throws IOException {

        try {
            if (FutureBazookaSafariAndroid.datenbank.register(fbs_username.getText(), fbs_passwort.getText())) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setScene(new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
                stage.setX(0);
                stage.setY(0);
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
        }

    }
}
