/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.FBS_MapController;
import FBS_Interfaces.FBS_TowerInterface;
import FBS_Maps.FBS_Safari_Map;
import FBS_Tower.FBS_Laser_Tower;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
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
            root = FXMLLoader.load(getClass().getResource("/Frontend/Spieloberflaeche.fxml"));
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
