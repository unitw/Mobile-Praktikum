package com.futurebazookasafariandroid;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLLoginScreenController {
    @FXML
    private void LoginPressed(ActionEvent event) throws IOException {
        System.out.println("pressed \"Log In\"");
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/futurebazookasafariandroid/FBS_MainMenu/FXMLMainMenu.fxml")); 
        
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setScene(new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }
    
    @FXML
    private void SignUpPressed(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/futurebazookasafariandroid/FBS_MainMenu/FXMLMainMenu.fxml")); 
        
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setScene(new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }
}
