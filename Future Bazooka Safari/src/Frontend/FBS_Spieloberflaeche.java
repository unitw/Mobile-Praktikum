/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.FBS_MapController;
import FBS_Maps.FBS_Safari_Map;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author rw
 */
public class FBS_Spieloberflaeche extends Pane {

    private Label l_leben = new Label();
    private Label l_gold = new Label();
    private TitledPane towermenu = new TitledPane();
    private Button b_settings = new Button();
    private SplitPane sp_pane = new SplitPane();
    private double width;
    private double height;

    public FBS_Spieloberflaeche(double width, double height) {
        this.width = width;
        this.height = height;
        initUI();
    }

    public void initUI() {

        FBS_Safari_Map map = new FBS_Safari_Map(width, height);
        FBS_Canvas canvas = new FBS_Canvas(map);
        FBS_MapController con = new FBS_MapController(map, canvas);

        this.getChildren().add(canvas);
        this.getChildren().add(l_leben);
        this.getChildren().add(l_gold);
        this.getChildren().add(towermenu);

        this.getChildren().add(b_settings);
        this.getChildren().add(sp_pane);

    }

}
