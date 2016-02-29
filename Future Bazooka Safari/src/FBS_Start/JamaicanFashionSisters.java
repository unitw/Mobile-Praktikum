/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rw
 */
public class JamaicanFashionSisters extends Application {
      
    @Override
    public void start(Stage stage) throws Exception { 
        Parent root = FXMLLoader.load(getClass().getResource("/FBS_LoginScreen/FXMLLoginScreen.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
//        BorderPane borderPane = new BorderPane();
//        WebView webView = new WebView();
//        String url = getClass().getResource("/FBS_HTML/index.html").toExternalForm();
//        webView.getEngine().load(url);
//        borderPane.setCenter(webView);
//        final Scene scene = new Scene(borderPane);
//        stage.setScene(scene);
//        stage.setHeight(512);
//        stage.setWidth(288);
//        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
