/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.Frontend;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author rw
 */
public class FBS_Spieloberflaeche extends AnchorPane {

    private Label l_leben = new Label("Leben:");
    private Label l_gold = new Label("Gold:");
    private Label l_runde = new Label("Runde:");
    private TitledPane towermenu = new TitledPane();
    private Button b_settings = new Button("Einstellungen");
    private SplitPane sp_pane = new SplitPane();
    private ScrollPane sc_pane = new ScrollPane();
    private boolean menuexists = false;
    private GridPane gp_overlay = new GridPane();
    private GridPane gp_infos = new GridPane();
    private double width;
    private double height;

    public FBS_Spieloberflaeche(double width, double height) {
        this.width = width;
        this.height = height;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/com/futurebazookasafariandroid/Frontend/Spieloberflaeche.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FBS_Spieloberflaeche.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        Scene scene = new Scene(root, width, height);
        Stage stage = new Stage();
        
        //set Stage boundaries to visible bounds of the main screen
        
        

        this.getChildren().add(root);

//        initMap();
//        initUI();
    }

}
