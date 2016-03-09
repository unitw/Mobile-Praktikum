/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.Backend;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_LevelInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MapInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MonsterInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_Spieler;
import com.futurebazookasafariandroid.FBS_Level.FBS_LevelFour;
import com.futurebazookasafariandroid.FBS_Level.FBS_LevelOne;
import com.futurebazookasafariandroid.FBS_Level.FBS_LevelThree;
import com.futurebazookasafariandroid.FBS_Level.FBS_LevelTwo;
import com.futurebazookasafariandroid.FBS_Maps.FBS_Safari_Map;
import com.futurebazookasafariandroid.Frontend.ControllerSpieloberflaeche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ObservableIntegerArray;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;

import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Marvin
 */
public class FBS_RundenController extends AnchorPane {

    private ArrayList<Integer> spawntimelist;
    private ArrayList<FBS_MonsterInterface> monsterlist;
    private static FBS_MapController mapcon;
    private static FBS_MapInterface map;
    private ControllerSpieloberflaeche controloverlay;
    private String tower;

    private static int runde = 0, schwierigkeit = 0;

    private static Parent root;
    private static Stage stg;

    private Image img = new Image("Lazertower.png");

    public FBS_RundenController(Stage s) {
        this.stg = s;
        Rectangle2D scr = Screen.getPrimary().getVisualBounds();
        this.map = new FBS_Safari_Map(500, 500);
        this.mapcon = new FBS_MapController(map);
        FBS_Spieler justus_jonas = new FBS_Spieler(700, 80000, 1000, 1);
        mapcon.setSpieler(justus_jonas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Spieloberflaeche.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(FBS_RundenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        controloverlay = fxmlLoader.<ControllerSpieloberflaeche>getController();
        controloverlay.setCanvas(mapcon.getCanvas());
        controloverlay.initStuff();

        for (Button b : controloverlay.getButtonlist()) {
            b.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {

                    tower = b.getText();

                    Dragboard db = b.startDragAndDrop(TransferMode.ANY);
                    /* Put a string on a dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(img);
                    db.setContent(content);
                    System.out.println("Drag started");
                    event.consume();
                }
            });
        }

        controloverlay.getCanvas().setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.MOVE);
                System.out.println("Drag Over");
                event.consume();
            }
        });
        controloverlay.getCanvas().setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                System.out.println(tower);
                mapcon.getMouseclicks(event.getX(), event.getY(), tower);
                System.out.println("X: " + event.getX() + "\nY: " + event.getY());
                event.setDropCompleted(true);
                event.consume();
            }
        });

        controloverlay.getB_settings().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAction();
            }
        });
        mapcon.getObserver().addListener(new ArrayChangeListener<ObservableIntegerArray>() {
            @Override
            public void onChanged(ObservableIntegerArray observableArray, boolean sizeChanged, int from, int to) {

                controloverlay.setGoldLabel(mapcon.getSpielergold());
                controloverlay.setLebenLabel(mapcon.getSpielerleben());
            }
        });

        controloverlay.setGoldLabel(mapcon.getSpielergold());
        controloverlay.setLebenLabel(mapcon.getSpielerleben());

        this.getChildren().add(root);

    }

    public void starteRunde(FBS_LevelInterface Runde) {

        runde++;
        controloverlay.setRundenLabel(runde);
        FBS_LevelInterface Level = Runde;
        this.monsterlist = Level.getMonster();
        this.spawntimelist = Level.getSpawntimes();
        mapcon.setMonsterspawnlist(this.monsterlist);
        mapcon.setSpawntimelist(spawntimelist);
        mapcon.initTimer();
    }

    public void mouseAction() {
        
        if (!mapcon.getRundenstatus() && runde%4 == 0) {
            starteRunde(new FBS_LevelOne(map.getStartpunkt().getX(), map.getStartpunkt().getY(), schwierigkeit));
            if(runde > 0){
                schwierigkeit++;
            }
        }
        if (!mapcon.getRundenstatus() && runde%4 == 1) {
            starteRunde(new FBS_LevelTwo(map.getStartpunkt().getX(), map.getStartpunkt().getY(), schwierigkeit));
        }
        if (!mapcon.getRundenstatus() && runde%4 == 2) {
            starteRunde(new FBS_LevelThree(map.getStartpunkt().getX(), map.getStartpunkt().getY(), schwierigkeit));
        }
        if (!mapcon.getRundenstatus() && runde%4 == 3) {
            starteRunde(new FBS_LevelFour(map.getStartpunkt().getX(), map.getStartpunkt().getY(), schwierigkeit));
        }

    }

    public static void roundFailed() {

        Rectangle2D scr = Screen.getPrimary().getVisualBounds();

        int pw = (int) scr.getWidth() / 4;
        int ph = (int) scr.getHeight() / 4;
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);

        Button reset = new Button("Try Again");
        Button menu = new Button("zum Menü");

        reset.setOnAction((javafx.event.ActionEvent event) -> {
            dialog.close();
            System.err.println("You're not fancy. Get Out");
            StackPane root = new StackPane();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            FBS_RundenController roundcon;
            runde = 0;
            schwierigkeit = 0;
            roundcon = new FBS_RundenController(stg);

            root.getChildren().add(roundcon);
            Scene scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());
            //set Stage boundaries to visible bounds of the main screen
            stg.setScene(scene);

        });

        menu.setOnAction((javafx.event.ActionEvent event) -> {
            dialog.close();
            
        });

        Label t = new Label();
        
        t.setCache(true);
        t.setText("You're not fancy");
        t.setId("GAMEOVER");
        t.getStyleClass().add("animated-gradient");

        // t.getStyleClass().add("animated-gradient");
        t.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 15));

        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);

        // t.setTranslateY(400);
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        

        
        pane.getStyleClass().add("bordered-titled-border");
        pane.add(t, 0, 0);
        pane.add(reset, 0, 1);
        pane.add(menu, 1, 1);
        dialog.setTitle("Game Over");
        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();

    }

}
