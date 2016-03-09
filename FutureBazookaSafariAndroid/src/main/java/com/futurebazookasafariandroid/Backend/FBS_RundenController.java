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
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;

import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    private String tower;
    private static int runde = 0;
    private Image img = new Image("Lazertower.png");

    public FBS_RundenController(ActionEvent e) throws IOException {
        Rectangle2D scr = Screen.getPrimary().getVisualBounds();
        this.map = new FBS_Safari_Map(500, 500);
        this.mapcon = new FBS_MapController(map, e);
        FBS_Spieler justus_jonas = new FBS_Spieler(700, 80000, 1000, 1);
        mapcon.setSpieler(justus_jonas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Spieloberflaeche.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        controloverlay = fxmlLoader.<ControllerSpieloberflaeche>getController();
        controloverlay.setCanvas(mapcon.getCanvas());
        controloverlay.initStuff();

        for (Button b : controloverlay.getButtonlist()) {
            b.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {

                    tower = b.getText();
                    System.out.println();

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
        if (!mapcon.getRundenstatus()) {
            starteRunde(new FBS_LevelOne(map.getStartpunkt().getX(), map.getStartpunkt().getY()));
        }

    }

    public static void roundFailed() {
        
          Stage dialog = new Stage();
                        dialog.initStyle(StageStyle.UNDECORATED);

                        Button reset = new Button("Try Again");

                        reset.setPrefSize(100, 40);
                        reset.setTranslateX(110);
                        reset.setTranslateY(80);
                        reset.setOnAction((javafx.event.ActionEvent event) -> {
                            dialog.close();
                           
                        });

                        Label l = new Label();
                        l.setPrefSize(324, 137);
                        l.setId("grave");
                        l.getTransforms().add(new Rotate(9, 50, 30));

                        Label t = new Label();
                        //  t.setTranslateY(160);
                        t.setTranslateX(75);
                        t.setTranslateY(10);
                        t.setCache(true);
                        t.setText("GAME OVER");
                        t.setId("GAMEOVER");
                        t.getStyleClass().add("animated-gradient");

                        t.setFont(Font.font(null, FontWeight.BOLD, 30));

                        ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

                        KeyValue keyValue1 = new KeyValue(baseColor, Color.RED);
                        KeyValue keyValue2 = new KeyValue(baseColor, Color.YELLOW);
                        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
                        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyValue2);
                        Timeline timeline = new Timeline(keyFrame1, keyFrame2);

                        baseColor.addListener((obs, oldColor, newColor) -> {
                            t.setStyle(String.format("-gradient-base: #%02x%02x%02x; ",
                                    (int) (newColor.getRed() * 255),
                                    (int) (newColor.getGreen() * 255),
                                    (int) (newColor.getBlue() * 255)));
                        });

                        timeline.setAutoReverse(true);
                        timeline.setCycleCount(Animation.INDEFINITE);
                        timeline.play();

                        Reflection r = new Reflection();
                        r.setFraction(0.7f);

                        t.setEffect(r);

                        // t.setTranslateY(400);
                        GridPane pane = new GridPane();
                        Scene scene = new Scene(pane);

                        pane.setPrefSize(300, 300);
                        pane.getStyleClass().add("bordered-titled-border");
                        pane.add(l, 0, 0);
                        pane.add(t, 0, 1);
                        pane.add(reset, 0, 2);
                        dialog.setMaxHeight(600);
                        dialog.setTitle("Game Over");
                        dialog.setScene(scene);
                        dialog.show();
        System.err.println("You're not fancy. Get Out");
        runde = 0;
        
    }

}
