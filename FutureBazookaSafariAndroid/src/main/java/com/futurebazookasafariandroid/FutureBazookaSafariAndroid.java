package com.futurebazookasafariandroid;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.futurebazookasafariandroid.FBS_DatenBank.FBS_DatenBank;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author rw
 */
public class FutureBazookaSafariAndroid extends Application {

    static public FBS_DatenBank datenbank;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        datenbank= new FBS_DatenBank();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginScreen.fxml"));

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());

        stage.setTitle("Future Bazooka Safari");
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
