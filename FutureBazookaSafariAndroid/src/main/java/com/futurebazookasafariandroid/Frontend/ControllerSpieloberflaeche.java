package com.futurebazookasafariandroid.Frontend;

import com.futurebazookasafariandroid.Backend.FBS_MapController;
import com.futurebazookasafariandroid.Backend.FBS_RundenController;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_Spieler;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_TowerInterface;
import com.futurebazookasafariandroid.FBS_Maps.FBS_Safari_Map;
import com.futurebazookasafariandroid.FBS_Tower.FBS_JustusJonas_Tower;
import com.futurebazookasafariandroid.FBS_Tower.FBS_Laser_Tower;
import java.util.ArrayList;
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
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class ControllerSpieloberflaeche {

    private boolean menuexists = false;
    FBS_MapController con;
    FBS_RundenController roundcon;

    @FXML
    private SplitPane sp_pane;

    @FXML
    private Label l_leben;

    @FXML
    private  Label l_runde;

    @FXML
    private GridPane gp_infos;

    @FXML
    private Label l_gold;

    @FXML
    private AnchorPane anchorpansplitunten;

    @FXML
    private AnchorPane anchorpansplitoben;

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
    
    private ArrayList<Button> buttonlist;
    
    @FXML
    private AnchorPane anchorpane1;

    @FXML
    private ScrollPane scrollpane;
    
    @FXML
    private Button start;
    
    

    public ScrollPane getScrollpane() {
        return scrollpane;
    }

    @FXML
    SplitPane pane = new SplitPane();

   

    private FBS_Canvas canvas1;

   

    @FXML
    protected void initialize() {
        
        
        
         b_settings.setStyle("-fx-padding: 8 15 15 15;\n"
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

        
    }

    public void initStuff() {
        
        buttonlist = new ArrayList();

        Rectangle2D scr = Screen.getPrimary().getVisualBounds();
        
        anchorpane.setPrefHeight(scr.getHeight());
        anchorpane.setPrefWidth(scr.getWidth());
        anchorpane1.setPrefHeight(canvas1.getMap().getMapsizex());
        anchorpane1.setPrefWidth(canvas1.getMap().getMapsizey());
        canvas1.prefWidth(this.getCanvas().getMap().getMapsizex());
        canvas1.prefHeight(this.getCanvas().getMap().getMapsizey());
        
        stackpane.setMaxHeight(scr.getHeight());
        stackpane.setMaxWidth(scr.getWidth());
        anchorpane1.getChildren().add(canvas1);
      
        initMap();
        zeichneTowerList();
        
        anchorpansplitoben.getChildren().add(zeichneTowerList());

        gp_overlay.setAlignment(Pos.TOP_RIGHT);

        
        stackpane.getChildren().remove(gp_overlay);
        stackpane.getChildren().add(gp_overlay);
        stackpane.setAlignment(gp_overlay, Pos.TOP_LEFT);
        stackpane.setAlignment(scrollpane,Pos.TOP_LEFT);

        pane.setOrientation(Orientation.VERTICAL);

        pane.getItems().add(zeichneTowerList());
        sc_pane.setContent(pane);

    }
//
//    public ControllerSpieloberflaeche(FBS_Canvas canvas) {
//        super();
//        
//        setRealCanvas(canvas);
//    }
//    
//    public void setRealCanvas(FBS_Canvas canvas){
//        canvas1 = canvas;
//        setCanvas();
//        
//    }

    public FBS_Canvas getCanvas() {
        return canvas1;
    }

    HashMap<String, FBS_TowerInterface> turmlist = new HashMap();

    public void initMap() {
        turmlist.put("Lasertower", new FBS_Laser_Tower());
        turmlist.put("JustusJonas", new FBS_JustusJonas_Tower());
       
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
            buttonlist.add(b_tower);
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
    
    public void setGoldLabel(int gold){
        this.l_gold.setText("Gold: " + gold);
    }
    
    public void setLebenLabel(int leben){
        this.l_leben.setText("Leben: " + leben);
    }
    public void setRundenLabel(int runde){
        this.l_runde.setText("Runde: " + runde);
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

    public Button getB_settings() {
        return b_settings;
    }

    public StackPane getStackpane() {
        return stackpane;
    }

    public AnchorPane getContainer() {
        return anchorpane;
    }

    public ArrayList<Button> getButtonlist() {
        return buttonlist;
    }

    public void setCanvas(FBS_Canvas canvas) {
        this.canvas1 = canvas;
    }

}
