/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.Frontend;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MapInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MonsterInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_Projektil_Interface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_TowerInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_HindernisInterface;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 *
 * @author rw
 */
public class FBS_Canvas extends Canvas {

    GraphicsContext gc = this.getGraphicsContext2D();
    FBS_MapInterface map;
    Image img = new Image("gras.png");
    Image img2 = new Image("erde.png");
    ArrayList<FBS_TowerInterface> towerlist = new ArrayList();
    ArrayList<FBS_HindernisInterface> hindernislist = new ArrayList();
    int ratio;
    int ZELLEN = 20;

    public FBS_Canvas(FBS_MapInterface map) {
        super(map.getMapsizex(), map.getMapsizey());
        this.map = map;
        this.ratio = (int) (map.getMapsizex() / this.ZELLEN);

        drawBackground();
    }

    public FBS_MapInterface getMap() {
        return map;
    }

    public GraphicsContext drawBackground() {
        Canvas can = new Canvas();
        can.setWidth(map.getMapsizex());
        can.setHeight(map.getMapsizey());

        GraphicsContext gc1 = can.getGraphicsContext2D();

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {

                gc1.drawImage(img, i * ratio, j * ratio, ratio, ratio);

            }
        }
        return gc1;
    }

    public void drawMap(ArrayList<FBS_MonsterInterface> monsterlist, ArrayList<FBS_TowerInterface> towerlist, ArrayList<FBS_Projektil_Interface> projektillist, ArrayList<FBS_HindernisInterface> hindernislist) {

//        gc.setFill(Color.GREEN);
//        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if(i == j) {
                    gc.drawImage(img2, i * ratio, j * ratio, ratio, ratio);
                } else {
                    gc.drawImage(img, i * ratio, j * ratio, ratio, ratio);
                }
            }
        }

//        gc.setFill(Color.CADETBLUE);
//        gc.fillRect(0, 0, map.getMapsizex(), map.getMapsizey());
        for (FBS_MonsterInterface mon : monsterlist) {
            gc.save();
            rotate(gc, mon.getangle(), mon.getPositionx() + mon.getGroesse() / 2, mon.getPositiony() + mon.getGroesse() / 2);
            gc.drawImage(mon.getPicture(), mon.getPositionx(), mon.getPositiony(), mon.getGroesse(), mon.getGroesse());
            gc.restore(); // back to original state (before rotation)
        }
        for (FBS_TowerInterface tower : towerlist) {
            gc.drawImage(tower.getPicture(), tower.getPositionx(), tower.getPositiony(), tower.getGroesse(), tower.getGroesse());

        }
        for (FBS_Projektil_Interface pro : projektillist) {
            gc.drawImage(pro.getPicture(), pro.getPositionx(), pro.getPositiony(), pro.getGroesse(), pro.getGroesse());

        }

        for (FBS_HindernisInterface hindernis : hindernislist) {
            gc.drawImage(hindernis.getPicture(), hindernis.getPositionx(), hindernis.getPositiony(), hindernis.getGroesse(), hindernis.getGroesse());
        }

    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

}
