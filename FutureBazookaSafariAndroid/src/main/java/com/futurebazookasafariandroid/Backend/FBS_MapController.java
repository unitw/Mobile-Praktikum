/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.Backend;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MapInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MonsterInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_Projektil_Interface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_Spieler;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_SpielerInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_TowerInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_HindernisInterface;
import com.futurebazookasafariandroid.FBS_Monster.FBS_Monster_Ratte;
import com.futurebazookasafariandroid.FBS_Projektile.FBS_LaserProjektil;
import com.futurebazookasafariandroid.FBS_Tower.FBS_Laser_Tower;
import com.futurebazookasafariandroid.Frontend.FBS_Canvas;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import static java.lang.Math.abs;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author rw
 */
public class FBS_MapController {

    FBS_MapInterface map;

    private ArrayList<FBS_MonsterInterface> monsterlist = new ArrayList();
    private ArrayList<FBS_TowerInterface> turmlist = new ArrayList();
    private ArrayList<FBS_Projektil_Interface> projektillist = new ArrayList();
    private ArrayList<FBS_HindernisInterface> hindernislist = new ArrayList();

    private AnimationTimer timer;
    private AnimationTimer projektiltimer;

    FBS_MonsterInterface monsterratte;

    FBS_SpielerInterface spieler;
    private int spielerleben;
    private int spielergold;
    private FBS_Canvas canvas;
    private int iteration = 0;

    public FBS_MapController(FBS_MapInterface map, FBS_Canvas canvas) {

        this.map = map;

        monsterratte = new FBS_Monster_Ratte((int) map.getStartpunkt().getX(), (int) map.getStartpunkt().getY());
        monsterlist.add(monsterratte);
        this.hindernislist = map.getHindernislist();

        this.canvas = canvas;
        spieler = new FBS_Spieler(0, 1000, 600, 100);
        spielerleben = spieler.getmaxLife();
        spielergold = spieler.getstartGold();
        //canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
        mouseactions();
        initTimer();

    }

    public void initTimer() {

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                MonsterMovement(iteration);
                canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
                TowerShoot(iteration);
                canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
                final ArrayList<FBS_Projektil_Interface> loeschliste = new ArrayList();

                if (FBS_MapController.this.getProjektillist().isEmpty()) {
                    return;
                }

                for (FBS_Projektil_Interface projektil : FBS_MapController.this.getProjektillist()) {
                    bewege(projektil);

                    if (projektil.getPositionx() == projektil.getTarget().getPositionx() && projektil.getPositiony() == projektil.getTarget().getPositiony()) {
                        loeschliste.add(projektil);

                        Schadensberechnung(projektil);
                    }
                }
                for (FBS_Projektil_Interface projektil : loeschliste) {
                    FBS_MapController.this.getProjektillist().remove(projektil);
                }

                iteration++;
                canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
            }

        };
        timer.start();

    }

    public void getMouseclicks(double x, double y) {
        FBS_TowerInterface tower = new FBS_Laser_Tower((int) x, (int) y);

        if (buildTower(tower)) {
            turmlist.add(tower);
            canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
        }

    }

    public void mouseactions() {
//        canvas.setOnTouchPressed(new EventHandler<TouchEvent>() {
//            @Override
//            public void handle(TouchEvent event) {
//                if (event.getTouchCount() == 1) {
//
//                    FBS_TowerInterface tower = new FBS_Laser_Tower((int) event.getTouchPoint().getSceneX(), (int) event.getTouchPoint().getSceneY());
//
//                    if (buildTower(tower)) {
//                        turmlist.add(tower);
//                        canvas.drawObject(tower);
//                    }
//
//                }
//                event.consume();
//            }
//        });

        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FBS_TowerInterface tower = new FBS_Laser_Tower((int) event.getSceneX(), (int) event.getSceneY());

                if (buildTower(tower)) {
                    turmlist.add(tower);
                    canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
                }

            }
        });
    }

    public boolean buildTower(FBS_TowerInterface tower) {

        Rectangle2D rect = new Rectangle2D(tower.getPositionx(), tower.getPositiony(), tower.getGroesse(), tower.getGroesse());

        for (FBS_TowerInterface tw : turmlist) {
            Rectangle2D rect1 = new Rectangle2D(tw.getPositionx(), tw.getPositiony(), tw.getGroesse(), tw.getGroesse());

            if (rect.intersects(rect1)) {
                return false;

            } else if (rect.contains(map.getEndpunkt())) {
                return false;
            }

        }
        for (FBS_HindernisInterface h : hindernislist) {
            Rectangle2D rect2 = new Rectangle2D(h.getPositionx(), h.getPositiony(), h.getGroesse(), h.getGroesse());
            if (rect.intersects(rect2)) {
                return false;
            }

        }
        return true;

    }

    private void TowerShoot(int iteration) {
        for (FBS_TowerInterface tower : turmlist) {
            if (iteration % tower.getAttackspeed() == 0) {
                ArrayList<FBS_MonsterInterface> aggrolist = inRange(tower);
                FireAction(tower, aggrolist);
            }
        }
    }

    public boolean buildHindernis(FBS_HindernisInterface hindernis) {

        Rectangle2D rect = new Rectangle2D(hindernis.getPositionx(), hindernis.getPositiony(), hindernis.getGroesse(), hindernis.getGroesse());

        for (FBS_HindernisInterface hn : hindernislist) {
            Rectangle2D rect1 = new Rectangle2D(hn.getPositionx(), hn.getPositiony(), hn.getGroesse(), hn.getGroesse());

            if (rect.intersects(rect1)) {
                return false;

            } else if (rect.contains(map.getEndpunkt())) {
                return false;
            }

        }
        return true;

    }

    public void Schadensberechnung(FBS_Projektil_Interface project) {

        FBS_MonsterInterface mon = project.getTarget();

        if (!this.getMonsterlist().contains(mon)) {
            return;
        } else {
            System.out.println("HIT");
            int lifemon = mon.getLife() - project.getDamage();
            if (lifemon <= 0) {
                this.getMonsterlist().remove(mon);
            } else {
                mon.setLife(lifemon);
            }
        }

    }

    public void FireAction(FBS_TowerInterface tower, ArrayList<FBS_MonsterInterface> aggrolist) {
        //Später mit Decision network zur verfeinerung der strategie
        FBS_MonsterInterface mon = null;

        if (!aggrolist.isEmpty()) {
            mon = aggrolist.get(0);

        }
        if (mon != null) {

            FBS_Projektil_Interface projektil = new FBS_LaserProjektil(mon, tower.getPositionx(), tower.getPositiony(), tower.getDamage(), tower.getAOE());
            this.getProjektillist().add(projektil);
        }

    }

    public int getCost(int sx, int sy, int tx, int ty) {

        int dx = abs(tx - sx);
        int dy = abs(ty - sy);
        int heuristic = dx + dy;

        return heuristic;
    }

    public void MonsterMovement(int i) {
        ArrayList<FBS_MonsterInterface> loeschliste = new ArrayList();

        for (FBS_MonsterInterface mon : this.getMonsterlist()) {

            if (i % mon.getSpeed() == 0) {
                bewege(mon);
                if (mon.getPositionx() == this.map.getEndpunkt().getX()
                        && mon.getPositiony() == this.map.getEndpunkt().getY()) {
                    this.setSpielerleben(spielerleben - 1);
                    loeschliste.add(mon);

                }
            }

        }

        for (FBS_MonsterInterface mon : loeschliste) {
            monsterlist.remove(mon);

        }

    }

    public FBS_SpielerInterface getSpieler() {
        return spieler;
    }

    public void setSpieler(FBS_SpielerInterface spieler) {
        this.spieler = spieler;
    }

    public int getSpielerleben() {
        return spielerleben;
    }

    public void setSpielerleben(int spielerleben) {
        this.spielerleben = spielerleben;
    }

    public int getSpielergold() {
        return spielergold;
    }

    public void setSpielergold(int spielergold) {
        this.spielergold = spielergold;
    }

    public void bewege(Object object) {

        int posx = 0;
        int posy = 0;
        if (object instanceof FBS_MonsterInterface) {

            FBS_MonsterInterface moveMon = (FBS_MonsterInterface) object;
            posx = moveMon.getPositionx();
            posy = moveMon.getPositiony();

            HashMap<Point2D, Integer> zuegemap = new HashMap<>();
            ArrayList<Point2D> zuege = new ArrayList();
            Point2D p1 = new Point2D(posx, posy - 1);
            Point2D p2 = new Point2D(posx + 1, posy - 1);
            Point2D p3 = new Point2D(posx + 1, posy);
            Point2D p4 = new Point2D(posx + 1, posy + 1);
            Point2D p5 = new Point2D(posx, posy + 1);
            Point2D p6 = new Point2D(posx - 1, posy + 1);
            Point2D p7 = new Point2D(posx - 1, posy);
            Point2D p8 = new Point2D(posx - 1, posy - 1);

            zuegemap.put(p1, 180);
            zuegemap.put(p2, 225);
            zuegemap.put(p3, 270);
            zuegemap.put(p4, 315);
            zuegemap.put(p5, 0);
            zuegemap.put(p6, 45);
            zuegemap.put(p7, 90);
            zuegemap.put(p8, 135);

            if (zugmoeglich(p1, moveMon)) {
                zuege.add(p1);
            }
            if (zugmoeglich(p2, moveMon)) {
                zuege.add(p2);
            }
            if (zugmoeglich(p3, moveMon)) {
                zuege.add(p3);
            }
            if (zugmoeglich(p4, moveMon)) {
                zuege.add(p4);
            }
            if (zugmoeglich(p5, moveMon)) {
                zuege.add(p5);
            }
            if (zugmoeglich(p6, moveMon)) {
                zuege.add(p6);
            }
            if (zugmoeglich(p7, moveMon)) {
                zuege.add(p7);
            }
            if (zugmoeglich(p8, moveMon)) {
                zuege.add(p8);
            }

            Point2D neuerZug = getnextZug(object, zuege);
            moveMon.insertzug(new Point2D(moveMon.getPositionx(), moveMon.getPositiony()));

            moveMon.setPosition((int) neuerZug.getX(), (int) neuerZug.getY());
            moveMon.setangle(zuegemap.get(neuerZug));

        } else if (object instanceof FBS_Projektil_Interface) {

            FBS_Projektil_Interface project = (FBS_Projektil_Interface) object;
            posx = project.getPositionx();
            posy = project.getPositiony();

            HashMap<Point2D, Integer> zuegemap = new HashMap<>();
            ArrayList<Point2D> zuege = new ArrayList();
            Point2D p1 = new Point2D(posx, posy - 1);
            Point2D p2 = new Point2D(posx + 1, posy - 1);
            Point2D p3 = new Point2D(posx + 1, posy);
            Point2D p4 = new Point2D(posx + 1, posy + 1);
            Point2D p5 = new Point2D(posx, posy + 1);
            Point2D p6 = new Point2D(posx - 1, posy + 1);
            Point2D p7 = new Point2D(posx - 1, posy);
            Point2D p8 = new Point2D(posx - 1, posy - 1);

            zuegemap.put(p1, 180);
            zuegemap.put(p2, 225);
            zuegemap.put(p3, 270);
            zuegemap.put(p4, 315);
            zuegemap.put(p5, 0);
            zuegemap.put(p6, 45);
            zuegemap.put(p7, 90);
            zuegemap.put(p8, 135);

            zuege.add(p1);

            zuege.add(p2);

            zuege.add(p3);

            zuege.add(p4);

            zuege.add(p5);

            zuege.add(p6);

            zuege.add(p7);

            zuege.add(p8);

            Point2D neuerZug = getnextZug(object, zuege);

            project.setPosition((int) neuerZug.getX(), (int) neuerZug.getY());
        }

    }

    public boolean zugmoeglich(Point2D Zielzug, FBS_MonsterInterface mon) {

        Rectangle2D rect1 = new Rectangle2D((int) Zielzug.getX(), (int) Zielzug.getY(), mon.getGroesse(), mon.getGroesse());

        for (FBS_TowerInterface tower : turmlist) {

            Rectangle2D rect = new Rectangle2D(tower.getPositionx(), tower.getPositiony(), tower.getGroesse(), tower.getGroesse());

            if (rect1.intersects(rect)) {

                return false;
            }
            if (!mon.testZug(Zielzug)) {
                return false;
            }

        }
        for (FBS_HindernisInterface h : hindernislist) {
            Rectangle2D rect2 = new Rectangle2D(h.getPositionx(), h.getPositiony(), h.getGroesse(), h.getGroesse());
            if (rect1.intersects(rect2)) {
                return false;
            }

        }
        return true;
    }

//        Hinderniss implementieren
//          for(FBS_TowerInterface tower:turmlist){
//            
//        }
    //A-Stern
    public Point2D getnextZug(Object o, ArrayList<Point2D> zuege) {

        int heuristic = Integer.MAX_VALUE;
        Point2D neuerZug = null;
        int posiytarget = 0;
        int posixtarget = 0;

        for (Point2D zug : zuege) {

            int posix = (int) zug.getX();
            int posiy = (int) zug.getY();
            if (o instanceof FBS_Projektil_Interface) {
                FBS_Projektil_Interface project = (FBS_Projektil_Interface) o;
                posixtarget = project.getTarget().getPositionx();
                posiytarget = project.getTarget().getPositiony();
            } else if (o instanceof FBS_MonsterInterface) {
                posixtarget = (int) this.map.getEndpunkt().getX();
                posiytarget = (int) this.map.getEndpunkt().getY();
            }

            int heuristicneu = (int) getCost(posix, posiy, posixtarget, posiytarget);

            if (heuristic > heuristicneu) {
                heuristic = heuristicneu;
                neuerZug = new Point2D(posix, posiy);
            }

        }

        return neuerZug;
    }

    public ArrayList<FBS_MonsterInterface> inRange(FBS_TowerInterface turm) {

        ArrayList<FBS_MonsterInterface> aggrolist = new ArrayList();

        int x1 = turm.getPositionx();
        int y1 = turm.getPositiony();

        //TODO
        //KD-Baum
        for (int x = Math.max(0, x1 - turm.getRange()); x < Math.min(this.getMap().getMapsizex(), x1 + turm.getRange() + 1); x++) {
            for (int y = Math.max(0, y1 - turm.getRange()); y < Math.min(this.getMap().getMapsizey(), y1 + turm.getRange() + 1); y++) {

                for (FBS_MonsterInterface mon1 : this.getMonsterlist()) {

                    if (x == mon1.getPositionx() && y == mon1.getPositiony()) {

                        aggrolist.add(mon1);
                    }

                }

            }
        }

        return aggrolist;
    }

    public ArrayList<FBS_MonsterInterface> getMonsterlist() {
        return monsterlist;
    }

    public void setMonsterlist(ArrayList<FBS_MonsterInterface> monsterlist) {
        this.monsterlist = monsterlist;
    }

    public ArrayList<FBS_TowerInterface> getTurmlist() {
        return turmlist;
    }

    public void setTurmlist(ArrayList<FBS_TowerInterface> turmlist) {
        this.turmlist = turmlist;
    }

    public void setHindernislist(ArrayList<FBS_HindernisInterface> hindernislist) {
        this.hindernislist = hindernislist;
    }

    public ArrayList<FBS_HindernisInterface> getHindernislist() {
        return this.hindernislist;
    }

    public ArrayList<FBS_Projektil_Interface> getProjektillist() {
        return projektillist;
    }

    public void setProjektillist(ArrayList<FBS_Projektil_Interface> projektillist) {
        this.projektillist = projektillist;
    }

    public FBS_MapInterface getMap() {
        return map;
    }

    public void setMap(FBS_MapInterface map) {
        this.map = map;
    }

}
