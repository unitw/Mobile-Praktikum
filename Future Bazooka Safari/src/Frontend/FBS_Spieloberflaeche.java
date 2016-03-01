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
import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Orientation;
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

/**
 *
 * @author rw
 */
public class FBS_Spieloberflaeche extends BorderPane {

    private Label l_leben = new Label("Leben:");
    private Label l_gold = new Label("Gold:");
    private TitledPane towermenu = new TitledPane();
    private Button b_settings = new Button("Einstellungen");
    private SplitPane sp_pane = new SplitPane();
    private ScrollPane sc_pane = new ScrollPane();

    private double width;
    private double height;

    public FBS_Spieloberflaeche(double width, double height) {
        this.width = width;
        this.height = height;
        initMap();
        initUI();
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
                        sp_pane.getItems().add(setContextPanetower(turmlist.get(name)));
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

        canvas.prefWidth(width / 2);
        canvas.prefHeight(height / 2);

        FBS_MapController con = new FBS_MapController(map, canvas);

        FlowPane labelpane = new FlowPane(l_leben, l_gold);

        towermenu.setText("Menu");
        sp_pane.setOrientation(Orientation.HORIZONTAL);

        towermenu.setContent(sp_pane);

        sp_pane.getItems().add(sc_pane);
        sc_pane.setContent(zeichneTowerList());

        this.setCenter(canvas);
        this.setTop(labelpane);
        this.setRight(towermenu);
        this.setBottom(b_settings);

    }

}
