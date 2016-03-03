package com.futurebazookasafariandroid.Frontend;

import com.futurebazookasafariandroid.Backend.FBS_MapController;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_TowerInterface;
import com.futurebazookasafariandroid.FBS_Maps.FBS_Safari_Map;
import com.futurebazookasafariandroid.FBS_Tower.FBS_Laser_Tower;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class ControllerSpieloberflaeche {

    private boolean menuexists = false;
    FBS_MapController con;

    @FXML
    private SplitPane sp_pane;

    @FXML
    private Label l_leben;

    @FXML
    private Label l_runde;

    @FXML
    private GridPane gp_infos;

    @FXML
    private Label l_gold;

    @FXML
    private Canvas canvas1;

    @FXML
    private AnchorPane anchorpansplit;

    @FXML
    private AnchorPane anchorpansplitunten;

    @FXML
    private AnchorPane anchorpansplitoben;

    @FXML
    private TitledPane towermenu;

    @FXML
    private Button b_settings;

    @FXML
    private StackPane stackpane;

    @FXML
    private GridPane gp_overlay;

    @FXML
    private ScrollPane sc_pane;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    void stackpaneclicked(MouseEvent event) {

        con.getMouseclicks(event.getSceneX(), event.getSceneY());

    }

    SplitPane pane = new SplitPane();

    @FXML
    protected void initialize() {

        Rectangle2D scr = Screen.getPrimary().getVisualBounds();

        FBS_Safari_Map map = new FBS_Safari_Map(scr.getWidth(), scr.getHeight());

        FBS_Canvas canvas = new FBS_Canvas(map);

        con = new FBS_MapController(map, canvas);
        initMap();
        zeichneTowerList();

//       canvas.setLayoutX(0);
//       canvas.setLayoutY(0);
//       canvas.setTranslateX(200);
        //anchorpane.getChildren().add(canvas);
        stackpane.getChildren().add(canvas);
        stackpane.setAlignment(canvas, Pos.TOP_LEFT);

        anchorpansplitoben.getChildren().add(zeichneTowerList());

        gp_overlay.setAlignment(Pos.TOP_RIGHT);

        stackpane.getChildren().remove(gp_overlay);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(10);
        gp_overlay.getColumnConstraints().add(column1);
        stackpane.getChildren().add(gp_overlay);
        stackpane.setAlignment(gp_overlay, Pos.TOP_LEFT);

        pane.setOrientation(Orientation.VERTICAL);

        pane.getItems().add(zeichneTowerList());
        sc_pane.setContent(pane);

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
                            pane.getItems().remove(1);
                        }

                        pane.getItems().add(1, setContextPanetower(turmlist.get(name)));

                        menuexists = true;
                    }

                }
            });

            gridpane.setOpacity(1);
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

}
