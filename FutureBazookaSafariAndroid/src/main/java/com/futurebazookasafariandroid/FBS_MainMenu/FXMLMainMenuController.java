/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_MainMenu;

import com.futurebazookasafariandroid.Frontend.FBS_Spieloberflaeche;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benjamin.wolf
 */
public class FXMLMainMenuController implements Initializable {

    @FXML
    private void newGamePressed(ActionEvent event) {
        System.out.println("pressed \"Neues Spiel\"");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        StackPane root = new StackPane();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        FBS_Spieloberflaeche flaeche = new FBS_Spieloberflaeche();
        root.getChildren().add(flaeche);
        Scene scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());

        
        //set Stage boundaries to visible bounds of the main screen
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void loadGamePressed(ActionEvent event) {
        System.out.println("pressed \"Spiel laden\"");
    }

    @FXML
    private void shopPressed(ActionEvent event) throws IOException {
        System.out.println("pressed \"Shop\"");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        BorderPane borderPane = new BorderPane();
        WebView webView = new WebView();
        webView.getEngine().load("http://localhost/projects/uni_web_shop/index.html");
        borderPane.setCenter(webView);
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setScene(new Scene(borderPane, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
        stage.show();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
