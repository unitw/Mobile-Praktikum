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
import com.futurebazookasafariandroid.FBS_Projektile.FBS_BobAndrewsProjektil;
import com.futurebazookasafariandroid.FBS_Projektile.FBS_LaserProjektil;
import com.futurebazookasafariandroid.FBS_Tower.FBS_JustusJonas_Tower;
import com.futurebazookasafariandroid.FBS_Tower.FBS_Laser_Tower;
import com.futurebazookasafariandroid.Frontend.FBS_Canvas;

import com.sun.javafx.collections.ObservableIntegerArrayImpl;
import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.javafx.scene.shape.ObservableFaceArrayImpl;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import static java.lang.Math.abs;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchPoint;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ObservableFaceArray;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author rw
 */
public class FBS_MapController {

    private FBS_MapInterface map;

    private ArrayList<FBS_MonsterInterface> monsterlist = new ArrayList();
    private ArrayList<FBS_MonsterInterface> monsterspawnlist = new ArrayList();
    private ArrayList<FBS_TowerInterface> turmlist = new ArrayList();
    private ArrayList<FBS_Projektil_Interface> projektillist = new ArrayList();
    private ArrayList<FBS_HindernisInterface> hindernislist = new ArrayList();
    private ArrayList<Integer> spawntimelist = new ArrayList();

    private AnimationTimer timer;
    private boolean is_in_round;
    private FBS_MonsterInterface monsterratte;
    private ArrayList<Point2D> path;
    private FBS_SpielerInterface spieler;
    private int spielerleben = 0;
    private int spielergold = 0;
    private ObservableIntegerArray observer = new ObservableIntegerArrayImpl(0, 1);

    private FBS_Canvas canvas;
    private int iteration = 0;

    public FBS_MapController(FBS_MapInterface map, ActionEvent e) throws IOException {

        this.canvas = new FBS_Canvas(map);
        this.map = map;
        this.hindernislist = map.getHindernislist();
        this.is_in_round = false;
        aktualisiereObserver();
        //spieler = new FBS_Spieler(0, 1000, 600, 100);
        //spielerleben = spieler.getmaxLife();
        //spielergold = spieler.getstartGold();
        canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
        mouseactions();

        //initTimer();
    }

    public void stopTimer() {
        timer.stop();
        this.is_in_round = false;
    }

    public void initTimer() {

        this.is_in_round = true;
        path = getSchnellsterWeg();
        Points2Tower();
        iteration = 0;
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (!spawntimelist.isEmpty()) {
                    if (iteration == spawntimelist.get(0)) {
                        FBS_MonsterInterface mon = monsterspawnlist.get(0);
                        monsterlist.add(mon);
                        spawntimelist.remove(0);
                        monsterspawnlist.remove(0);
                    }
                }
                if (monsterlist.isEmpty() && monsterspawnlist.isEmpty() && projektillist.isEmpty()) {
                    stopTimer();
                }
                MonsterMovement(iteration);
                canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
                TowerShoot(iteration);
                canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
                final ArrayList<FBS_Projektil_Interface> loeschliste = new ArrayList();

                iteration++;

                if (FBS_MapController.this.getProjektillist().isEmpty()) {
                    return;
                }

                for (FBS_Projektil_Interface projektil : FBS_MapController.this.getProjektillist()) {
                    bewege(projektil);
                    Rectangle2D projectile = new Rectangle2D(projektil.getPositionx(), projektil.getPositiony(), projektil.getGroesse(), projektil.getGroesse());
                    Rectangle2D target = new Rectangle2D(projektil.getTarget().getPositionx(), projektil.getTarget().getPositiony(),
                            projektil.getTarget().getGroesse(), projektil.getTarget().getGroesse());

                    if (projectile.intersects(target)) {
                        loeschliste.add(projektil);

                        Schadensberechnung(projektil);
                    }
                }
                for (FBS_Projektil_Interface projektil : loeschliste) {
                    FBS_MapController.this.getProjektillist().remove(projektil);
                }

                canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
            }

        };
        timer.start();

    }

    public void getMouseclicks(double x, double y, String Towername) {
        FBS_TowerInterface tower;

        if (Towername.equals("JustusJonas")) {
            tower = new FBS_JustusJonas_Tower((int) x, (int) y);
        } else {
            tower = new FBS_Laser_Tower((int) x, (int) y);
        }

        if (buildTower(tower)) {
            turmlist.add(tower);
            spielergold -= tower.getBaukosten();
            aktualisiereObserver();
            canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);

        }
        //TODO : Show tower Range/stats;
        //  monsterlist.add(new FBS_Monster_Ratte((int) map.getStartpunkt().getX(), (int) map.getStartpunkt().getY()));

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

        //     canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
        //       @Override
        //     public void handle(MouseEvent event) {
        //       FBS_TowerInterface tower = new FBS_Laser_Tower((int) event.getSceneX(), (int) event.getSceneY());
//        if (buildTower(tower)) {
//            turmlist.add(tower);
//
//        }
//    }
//}
//);
    }

    public void addTower(FBS_TowerInterface tw) {
        if (buildTower(tw)) {
            turmlist.add(tw);
            canvas.drawMap(monsterlist, turmlist, projektillist, hindernislist);
        }
    }

    public boolean buildTower(FBS_TowerInterface tower) {

        Rectangle2D rect = new Rectangle2D(tower.getPositionx(), tower.getPositiony(), tower.getGroesse(), tower.getGroesse());
        if (spielergold < tower.getBaukosten()) {
            return false;
        }
        if (is_in_round) {
            return false;
        }
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

                ArrayList<FBS_MonsterInterface> aggrolist = new ArrayList();

                for (FBS_MonsterInterface mon : monsterlist) {

                    if (tower.getIntegerList().contains(mon.getWaypoint())) {

                        aggrolist.add(mon);

                    }

                }

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
                spielergold += mon.getLoot();
                aktualisiereObserver();

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
            FBS_Projektil_Interface projektil;
            if (tower.getName().equals("JustusJonas")) {
                projektil = new FBS_BobAndrewsProjektil(mon, tower.getPositionx(), tower.getPositiony(), tower.getDamage(), tower.getAOE());
            } else {
                projektil = new FBS_LaserProjektil(mon, tower.getPositionx(), tower.getPositiony(), tower.getDamage(), tower.getAOE());
            }

            this.getProjektillist().add(projektil);
        }

    }

    public void Points2Tower() {

        int i = 0;
        for (Point2D p : path) {

            for (FBS_TowerInterface t : turmlist) {

                Circle circ = new Circle();
                circ.setCenterX(t.getPositionx() + t.getGroesse() / 2);
                circ.setCenterY(t.getPositiony() + t.getGroesse() / 2);
                circ.setRadius(t.getRange() + t.getGroesse() / 2);

                if (circ.contains(p)) {

                    t.addInteger(i);
                }

            }
            i++;
        }

    }

    public double getCost(double sx, double sy, double tx, double ty) {

        double dx = abs(tx - sx);
        double dy = abs(ty - sy);
        double heuristic = dx + dy;

        return heuristic;
    }

    public void MonsterMovement(int i) {
        ArrayList<FBS_MonsterInterface> loeschliste = new ArrayList();

        for (FBS_MonsterInterface mon : this.getMonsterlist()) {

            if (i % mon.getSpeed() == 0) {
                bewege2(mon);

                if (mon.getPositionx() >= this.map.getEndpunkt().getX()
                        && mon.getPositiony() >= this.map.getEndpunkt().getY()) {
                    if (spielerleben == 0) {
                        FBS_RundenController.roundFailed();
                        stopTimer();
                        return;
                    }
                    this.setSpielerleben(spielerleben - 1);
                    aktualisiereObserver();
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
        this.setSpielergold(spieler.getstartGold());
        this.setSpielerleben(spieler.getmaxLife());
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

    public ArrayList<Point2D> getSchnellsterWeg() {
        Point2D start = map.getStartpunkt();
        Point2D end = map.getEndpunkt();
        System.out.println(end.toString());
        FBS_MonsterInterface moveMon = new FBS_Monster_Ratte(start.getX(), start.getY());
        //Key = point value = vorgänger;
        ArrayList<FBS_Knoten> open = new ArrayList();
        ArrayList<FBS_Knoten> closed = new ArrayList();
        open.add(new FBS_Knoten(0, null, start.getX(), start.getY()));
        while (true) {
            FBS_Knoten current = getBestPoint(open);

            open.remove(current);
            closed.add(current);
            if (current.getKnoten().getX() >= end.getX()
                    && current.getKnoten().getY() >= end.getY()) {
                ArrayList<Point2D> weg = new ArrayList();
                while (current.getVorgaenger() != null) {
                    weg.add(current.getKnoten());
                    current = current.getVorgaenger();
                }
                Collections.reverse(weg);
                return weg;
            }
            ArrayList<FBS_Knoten> neighbours = getNachbarKnoten(current);
            for (FBS_Knoten n : neighbours) {
                if (zugmoeglich(n.getKnoten(), moveMon) && (getNodeinList(closed, n) == null)) {
                    FBS_Knoten oldNode = getNodeinList(open, n);
                    if (oldNode == null) {
                        open.add(n);
                    } else if (oldNode.getPfadlaenge() > n.getPfadlaenge()) {
                        open.remove(oldNode);
                        open.add(n);
                    }

                }
            }

        }
    }

    public FBS_Knoten getNodeinList(ArrayList<FBS_Knoten> list, FBS_Knoten n) {
        for (FBS_Knoten a : list) {
            if (a.getKnoten().equals(n.getKnoten())) {
                return a;
            }
        }
        return null;
    }

    public FBS_Knoten getBestPoint(ArrayList<FBS_Knoten> list) {
        double min = Double.MAX_VALUE;
        FBS_Knoten returnKnoten = null;
        for (FBS_Knoten node : list) {
            double newmin = getCost(node.getKnoten().getX(), node.getKnoten().getY(), this.getMap().getEndpunkt().getX(), this.getMap().getEndpunkt().getY());
            if (newmin < min) {
                returnKnoten = node;
                min = newmin;
            }
        }
        return returnKnoten;
    }

    public ArrayList<FBS_Knoten> getNachbarKnoten(FBS_Knoten c) {
        FBS_Knoten k1 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX(), c.getKnoten().getY() - 10);
        FBS_Knoten k2 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX() + 10, c.getKnoten().getY() - 10);
        FBS_Knoten k3 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX() + 10, c.getKnoten().getY());
        FBS_Knoten k4 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX() + 10, c.getKnoten().getY() + 10);
        FBS_Knoten k5 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX(), c.getKnoten().getY() + 10);
        FBS_Knoten k6 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX() - 10, c.getKnoten().getY() + 10);
        FBS_Knoten k7 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX() - 10, c.getKnoten().getY());
        FBS_Knoten k8 = new FBS_Knoten(c.getPfadlaenge() + 1, c, c.getKnoten().getX() - 10, c.getKnoten().getY() - 10);
        ArrayList<FBS_Knoten> erg = new ArrayList();
        erg.add(k1);
        erg.add(k2);
        erg.add(k3);
        erg.add(k4);
        erg.add(k5);
        erg.add(k6);
        erg.add(k7);
        erg.add(k8);
        return erg;

    }

    public void bewege2(FBS_MonsterInterface moveMon) {
        Point2D newZug = path.get(moveMon.getWaypoint());
        double difx = newZug.getX() - moveMon.getPositionx();
        double dify = newZug.getY() - moveMon.getPositiony();
        moveMon.setangle(getMoveAngle(difx, dify));
        moveMon.setPosition(newZug.getX(), newZug.getY());
        moveMon.setWaypoint(moveMon.getWaypoint() + 1);
    }

    public void bewege(Object object) {

        double posx = 0;
        double posy = 0;
        if (object instanceof FBS_MonsterInterface) {

            FBS_MonsterInterface moveMon = (FBS_MonsterInterface) object;

            posx = moveMon.getPositionx();
            posy = moveMon.getPositiony();

            HashMap<Point2D, Integer> zuegemap = new HashMap<>();
            ArrayList<Point2D> zuege = new ArrayList();
            Point2D p1 = new Point2D(posx, posy - 5);
            Point2D p2 = new Point2D(posx + 5, posy - 5);
            Point2D p3 = new Point2D(posx + 5, posy);
            Point2D p4 = new Point2D(posx + 5, posy + 5);
            Point2D p5 = new Point2D(posx, posy + 5);
            Point2D p6 = new Point2D(posx - 5, posy + 5);
            Point2D p7 = new Point2D(posx - 5, posy);
            Point2D p8 = new Point2D(posx - 5, posy - 5);

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
            Point2D p1 = new Point2D(posx, posy - 10);
            Point2D p2 = new Point2D(posx + 10, posy - 10);
            Point2D p3 = new Point2D(posx + 10, posy);
            Point2D p4 = new Point2D(posx + 10, posy + 10);
            Point2D p5 = new Point2D(posx, posy + 10);
            Point2D p6 = new Point2D(posx - 10, posy + 10);
            Point2D p7 = new Point2D(posx - 10, posy);
            Point2D p8 = new Point2D(posx - 10, posy - 10);

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

        if (Zielzug.getX() < 0 || Zielzug.getY() < 0) {
            return false;
        }
        // if (Zielzug.getX() > this.map.getMapsizex() || Zielzug.getY() > this.map.getMapsizey()) {
        //    return false;
        //}

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

        double heuristic = Double.MAX_VALUE;
        Point2D neuerZug = null;
        double posiytarget = 0;
        double posixtarget = 0;

        for (Point2D zug : zuege) {

            double posix = zug.getX();
            double posiy = zug.getY();
            if (o instanceof FBS_Projektil_Interface) {
                FBS_Projektil_Interface project = (FBS_Projektil_Interface) o;
                posixtarget = project.getTarget().getPositionx() + (project.getTarget().getGroesse() / 2);
                posiytarget = project.getTarget().getPositiony() + (project.getTarget().getGroesse() / 2);
            } else if (o instanceof FBS_MonsterInterface) {
                posixtarget = this.map.getEndpunkt().getX();
                posiytarget = this.map.getEndpunkt().getY();
            }

            double heuristicneu = getCost(posix, posiy, posixtarget, posiytarget);

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

    public ArrayList<FBS_MonsterInterface> getMonsterspawnlist() {
        return monsterspawnlist;
    }

    public void setMonsterspawnlist(ArrayList<FBS_MonsterInterface> monsterspawnlist) {
        this.monsterspawnlist = monsterspawnlist;
    }

    public ArrayList<Integer> getSpawntimelist() {
        return spawntimelist;
    }

    public void setSpawntimelist(ArrayList<Integer> spawntimelist) {
        this.spawntimelist = spawntimelist;
    }

    public boolean getRundenstatus() {
        return is_in_round;
    }

    private int getMoveAngle(double difx, double dify) {
        switch ((int) difx) {
            case -10:
                switch ((int) dify) {
                    case 10:
                        return 45;
                    case 0:
                        return 90;
                    case -10:
                        return 135;
                }
            case 0:
                switch ((int) dify) {
                    case 10:
                        return 0;
                    case -10:
                        return 180;
                }
            case 10:
                switch ((int) dify) {
                    case 10:
                        return 315;
                    case 0:
                        return 270;
                    case -10:
                        return 225;
                }
            default:
                return 0;
        }
    }

    public FBS_Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(FBS_Canvas canvas) {
        this.canvas = canvas;
    }

    public ObservableIntegerArray getObserver() {
        return observer;
    }

    public void setObserver(ObservableIntegerArray observer) {
        this.observer = observer;
    }

    private void aktualisiereObserver() {
        observer.set(0, spielergold);
        observer.set(1, spielerleben);
    }

}
