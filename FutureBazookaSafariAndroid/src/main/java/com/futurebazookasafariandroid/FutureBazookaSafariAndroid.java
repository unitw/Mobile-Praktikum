package com.futurebazookasafariandroid;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rw
 */
public class FutureBazookaSafariAndroid extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginScreen.fxml")); 
        Scene scene = new Scene(root, 288, 512);
        
        stage.setTitle("Future Bazooka Safari");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}