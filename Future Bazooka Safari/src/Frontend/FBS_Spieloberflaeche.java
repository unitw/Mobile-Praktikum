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
        
        this.getChildren().add(root);
        
        
        
        
//        initMap();
//        initUI();
    }

    HashMap<String, FBS_TowerInterface> turmlist = new HashMap();

    public void initMap() {
        turmlist.put("Lasertower", new FBS_Laser_Tower());
        turmlist.put("Lasertower1", new FBS_Laser_Tower());
        turmlist.put("Lasertower2", new FBS_Laser_Tower());
        turmlist.put("Lasertower3", new FBS_Laser_Tower());
        turmlist.put("Lasertower4", new FBS_Laser_Tower());
        turmlist.put("Lasertower5", new FBS_Laser_Tower());

    }

    public GridPane zeichneTowerList() {

        GridPane gridpane = new GridPane();

        int anzahltuerme = turmlist.keySet().size();

        int i = 0;
        for (String tower : turmlist.keySet()) {

            FBS_TowerInterface tower2 = turmlist.get(tower);
            tower2.setGroesse(32);

            Image img = tower2.getPicture();
            ImageView imgview = new ImageView(img);
            imgview.setFitHeight(tower2.getGroesse());
            imgview.setFitWidth(tower2.getGroesse());

            Button b_tower = new Button(tower, imgview);
            b_tower.setId(tower);
            b_tower.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (event.getSource() instanceof Button) {

                        Button b_event = (Button) event.getSource();

                        String name = b_event.getId();
                        if (menuexists) {
                            sp_pane.getItems().remove(1);
                        }
                        sp_pane.getItems().add(setContextPanetower(turmlist.get(name)));
                        menuexists = true;
                    }

                }
            });

            gridpane.add(b_tower, i % 2, i / 2);
            i++;
        }

        return gridpane;
    }

    public GridPane setContextPanetower(FBS_TowerInterface tower) {
        GridPane gridpane = new GridPane();

        gridpane.add(new Label("Range: " + tower.getRange()), 0, 0);
        gridpane.add(new Label("Damage: " + tower.getDamage()), 0, 1);
        gridpane.add(new Label("AOE: " + tower.isAOE()), 0, 2);
        gridpane.add(new Label("Attackspeed: " + tower.getAttackspeed()), 0, 3);
        gridpane.add(new Label("Baukosten: " + tower.getBaukosten()), 0, 4);

        return gridpane;
    }

    public void initUI() {

        FBS_Safari_Map map = new FBS_Safari_Map(width, height);
        FBS_Canvas canvas = new FBS_Canvas(map);

        canvas.prefWidth(width);
        canvas.prefHeight(height);

        FBS_MapController con = new FBS_MapController(map, canvas);

        FlowPane labelpane = new FlowPane(l_leben, l_gold);

        gp_infos.add(l_gold, 0, 0);
        gp_infos.add(l_leben, 1, 0);
        gp_infos.add(l_runde, 2, 0);

        towermenu.setText("Menu");
        sp_pane.setOrientation(Orientation.VERTICAL);

        towermenu.setContent(sp_pane);

        sp_pane.getItems().add(sc_pane);
        sc_pane.setContent(zeichneTowerList());

        gp_overlay.add(gp_infos, 0, 0);
        gp_overlay.add(towermenu, 3, 0, 1, 4);
        gp_overlay.add(b_settings, 0, 3);
        gp_overlay.prefWidth(width);
        gp_overlay.prefHeight(height);

        this.getChildren().add(canvas);
        this.getChildren().add(gp_overlay);

    }

}
