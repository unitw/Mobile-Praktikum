/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Start;

import Backend.FBS_MapController;
import FBS_Maps.FBS_Safari_Map;
import Frontend.FBS_Canvas;
import Frontend.FBS_Spieloberflaeche;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author rw
 */
public class JamaicanFashionSisters extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/FBS_LoginScreen/FXMLLoginScreen.fxml"));

        StackPane root = new StackPane();
        
       

         Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
         
          FBS_Spieloberflaeche flaeche = new FBS_Spieloberflaeche(primScreenBounds.getWidth(),primScreenBounds.getHeight());
       
        root.getChildren().add(flaeche);
         
         
        
        Scene scene = new Scene(root, primScreenBounds.getWidth(),primScreenBounds.getHeight());

        stage.setTitle("Canvas");
        stage.setScene(scene);
        stage.show();
        
//        BorderPane borderPane = new BorderPane();
//        WebView webView = new WebView();
//        webView.getEngine().load("http://localhost/projects/uni_web_shop/index.html");
//        borderPane.setCenter(webView);
//        Scene scene = new Scene(borderPane);
//        stage.setScene(scene);
//        stage.setHeight(512);
//        stage.setWidth(288);
//        stage.setResizable(false);
//        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
