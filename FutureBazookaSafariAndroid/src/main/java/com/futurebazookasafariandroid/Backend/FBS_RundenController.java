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
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_SpielerInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_TowerInterface;
import com.futurebazookasafariandroid.FBS_Level.FBS_LevelOne;
import com.futurebazookasafariandroid.FBS_Maps.FBS_Safari_Map;
import com.futurebazookasafariandroid.FBS_Tower.FBS_Laser_Tower;
import com.futurebazookasafariandroid.Frontend.ControllerSpieloberflaeche;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableIntegerArray;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

/**
 *
 * @author Marvin
 */
public class FBS_RundenController extends AnchorPane {

    private ArrayList<Integer> spawntimelist;
    private ArrayList<FBS_MonsterInterface> monsterlist;
    private FBS_MapController mapcon;
    private FBS_MapInterface map;
    private ControllerSpieloberflaeche controloverlay;
    private int runde = 0;
    private boolean touchMovedFlag = false;


    public FBS_RundenController(ActionEvent e) throws IOException {        
        Rectangle2D scr = Screen.getPrimary().getVisualBounds();
        this.map = new FBS_Safari_Map(1500, 1500);
        this.mapcon = new FBS_MapController(map, e);
        FBS_Spieler justus_jonas = new FBS_Spieler(700, 80000, 1000, 1);
        mapcon.setSpieler(justus_jonas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Spieloberflaeche.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        controloverlay = fxmlLoader.<ControllerSpieloberflaeche>getController();
        controloverlay.setCanvas(mapcon.getCanvas());
        controloverlay.initStuff();
        controloverlay.getCanvas().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapcon.getMouseclicks(event.getX(), event.getY());
                System.out.println("Mousepoints" + event.getX() + ", " + event.getY());

            }
        });
        
        controloverlay.getCanvas().addEventFilter(TouchEvent.TOUCH_MOVED, new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
                touchMovedFlag = true;
            }
        });
        controloverlay.getCanvas().addEventFilter(TouchEvent.TOUCH_RELEASED, new EventHandler<TouchEvent>() {
            @Override
            public void handle(TouchEvent event) {
                if(touchMovedFlag) {
                    System.out.println("sorry, you moved");
                } else {
                    System.out.println("Touchpoints" + event.getTouchPoint().getX() + ", " + event.getTouchPoint().getY());
                }
                touchMovedFlag = false;
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
        if (!mapcon.getRundenstatus()) {
            starteRunde(new FBS_LevelOne(map.getStartpunkt().getX(), map.getStartpunkt().getY()));
        }

    }

}
