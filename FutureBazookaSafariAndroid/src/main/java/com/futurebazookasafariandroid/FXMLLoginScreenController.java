package com.futurebazookasafariandroid;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLLoginScreenController {
    @FXML
    private void LoginPressed(ActionEvent event) throws IOException {
        System.out.println("pressed \"Log In\"");
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/futurebazookasafariandroid/FBS_MainMenu/FXMLMainMenu.fxml")); 
        
        stage.setScene(new Scene(root, 288, 512));
        stage.show();
    }
    
    @FXML
    private void SignUpPressed(ActionEvent event) throws IOException{
        System.out.println("pressed \"Sign Up\"");
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/futurebazookasafariandroid/FBS_MainMenu/FXMLMainMenu.fxml")); 
        
        stage.setScene(new Scene(root, 288, 512));
        stage.show();
    }
}
