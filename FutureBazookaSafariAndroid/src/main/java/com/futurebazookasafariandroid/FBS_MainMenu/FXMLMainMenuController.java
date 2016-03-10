/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_MainMenu;

import com.futurebazookasafariandroid.Backend.FBS_RundenController;
import com.futurebazookasafariandroid.FXMLLoginScreenController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benjamin.wolf
 */
public class FXMLMainMenuController {

    @FXML
    private Button newgame;

    @FXML
    private Button loadgame;
    @FXML
    private Button shop;

    @FXML
    private Label Futurelogo;

    @FXML
    private void newGamePressed(ActionEvent event) throws IOException {
        System.out.println("pressed \"Neues Spiel\"");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        StackPane root = new StackPane();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        FBS_RundenController roundcon = new FBS_RundenController(stage);
        root.getChildren().add(roundcon);
        Scene scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());
        //set Stage boundaries to visible bounds of the main screen
        stage.setScene(scene);
        stage.show();
//
//        
//        
        //      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        StackPane root = new StackPane();
//
        //       Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //       FBS_Spieloberflaeche flaeche = new FBS_Spieloberflaeche();
        //       root.getChildren().add(flaeche);
        //      Scene scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());
//        
//        
//        //set Stage boundaries to visible bounds of the main screen
        //       stage.setScene(scene);
        //      stage.show();

    }

    @FXML
    private void loadGamePressed(ActionEvent event) {
        System.out.println("pressed \"Spiel laden\"");
    }

    @FXML
    private void shopPressed(ActionEvent event) throws IOException {
        System.out.println("pressed \"Shop\"");
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        BorderPane borderPane = new BorderPane();
//        WebView webView = new WebView();
//        webView.getEngine().load("http://localhost/projects/uni_web_shop/index.html");
//        borderPane.setCenter(webView);
//        
//        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//        stage.setScene(new Scene(borderPane, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
//        stage.show();
    }

    @FXML
    public void initialize() {

//        
        newgame.setStyle("-fx-padding: 8 15 15 15;\n"
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-background-color: \n"
                + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n"
                + "        #9d4024,\n"
                + "        #d86e3a,\n"
                + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n"
                + "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 1.1em;"
        );

        loadgame.setStyle("-fx-padding: 8 15 15 15;\n"
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-background-color: \n"
                + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n"
                + "        #9d4024,\n"
                + "        #d86e3a,\n"
                + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n"
                + "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 1.1em;");

        shop.setStyle("-fx-padding: 8 15 15 15;\n"
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-background-color: \n"
                + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n"
                + "        #9d4024,\n"
                + "        #d86e3a,\n"
                + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n"
                + "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-font-size: 1.1em;");
//        

    }

}
