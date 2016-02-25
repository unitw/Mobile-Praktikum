/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import FBS_Interfaces.FBS_MonsterInterface;
import FBS_Interfaces.FBS_Projektil_Interface;
import FBS_Interfaces.FBS_TowerInterface;
import FBS_Monster.FBS_Monster_Ratte;
import FBS_Projektile.FBS_LaserProjektil;
import FBS_Tower.FBS_Laser_Tower;
import java.awt.Point;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;

/**
 *
 * @author rw
 */
public class FBS_MapController {

    int[][] map = new int[10][10];

    private ArrayList<FBS_MonsterInterface> monsterlist = new ArrayList();
    private ArrayList<FBS_TowerInterface> turmlist = new ArrayList();
    private ArrayList<FBS_Projektil_Interface> projektillist = new ArrayList();

    FBS_MonsterInterface monsterratte = new FBS_Monster_Ratte(0, 0);
    FBS_TowerInterface lasertower = new FBS_Laser_Tower(5, 5);
    private AnimationTimer timer;

    public FBS_MapController(FBS_Map map) {

        monsterlist.add(monsterratte);
        turmlist.add(lasertower);

    }
    int iteration = 0;

    public void initTimer() {

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                MonsterMovement(iteration);
                for (FBS_TowerInterface tower : turmlist) {
                    ArrayList<FBS_MonsterInterface> aggrolist = inRange(tower);
                    FireAction(tower, aggrolist);

                }

                iteration++;

            }
        };

    }

    public void animationTimer(final FBS_Projektil_Interface projektil2) {

        AnimationTimer projektiltimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                FBS_Projektil_Interface projektil1 = projektil2;

                bewege(projektil1);
                if (projektil1.getPositionx() == projektil1.getTarget().getPositionx() && projektil1.getPositiony() == projektil1.getTarget().getPositiony()) {

                 //TODO
                    //   projektiltimer.stop();
                }
            }

        };
        projektiltimer.start();
    }

    public void Schadensberechnung(FBS_Projektil_Interface project) {
        
        FBS_MonsterInterface mon= project.getTarget();
        
        int lifemon= mon.getLife()-project.getDamage();
        if(lifemon<=0){
            this.getMonsterlist().remove(mon);
        }else{
            mon.setLife(lifemon);
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

        for (FBS_MonsterInterface mon : this.getMonsterlist()) {

            if (i % mon.getSpeed() == 0) {
                bewege(mon);
            }

        }

    }

    public void bewege(Object object) {

        if (object instanceof FBS_MonsterInterface) {

            FBS_MonsterInterface moveMon = (FBS_MonsterInterface) object;
            int posxakt = moveMon.getPositionx();

            int posyakt = moveMon.getPositiony();

            moveMon.setPosition(posxakt + 4, posyakt);

        } else if (object instanceof FBS_Projektil_Interface) {

            FBS_Projektil_Interface project = (FBS_Projektil_Interface) object;
            int posx = project.getPositionx();
            int posy = project.getPositiony();

            ArrayList<Point> zuege = new ArrayList();

            Point p1 = new Point(posx, posy + 1);
            Point p2 = new Point(posx + 1, posy + 1);
            Point p3 = new Point(posx + 1, posy);
            Point p4 = new Point(posx + 1, posy - 1);
            Point p5 = new Point(posx, posy - 1);
            Point p6 = new Point(posx - 1, posy - 1);
            Point p7 = new Point(posx - 1, posy);
            Point p8 = new Point(posx - 1, posy + 1);

            zuege.add(p1);
            zuege.add(p2);
            zuege.add(p3);
            zuege.add(p4);
            zuege.add(p5);
            zuege.add(p6);
            zuege.add(p7);
            zuege.add(p8);

            Point neuerZug = getnextZug(project, zuege);

            project.setPosition((int) neuerZug.getX(), (int) neuerZug.getY());

        }

    }

    //A-Stern
    public Point getnextZug(FBS_Projektil_Interface project, ArrayList<Point> zuege) {

        int heuristic = Integer.MAX_VALUE;
        Point neuerZug = null;

        for (Point zug : zuege) {

            int posix = (int) zug.getX();
            int posiy = (int) zug.getY();

            int posixtarget = project.getTarget().getPositionx();
            int posiytarget = project.getTarget().getPositiony();

            int heuristicneu = (int) getCost(posix, posiy, posixtarget, posiytarget);

            if (heuristic > heuristicneu) {
                heuristic = heuristicneu;
                neuerZug = new Point(posix, posiy);
            }

        }

        return neuerZug;
    }

    public ArrayList<FBS_MonsterInterface> inRange(FBS_TowerInterface turm) {

        ArrayList<FBS_MonsterInterface> aggrolist = new ArrayList();

        int x1 = turm.getPositionx();
        int y1 = turm.getPositiony();

        for (int x = Math.max(0, x1 - turm.getRange()); x < Math.min(this.getMap()[0].length, x1 + turm.getRange() + 1); x++) {
            for (int y = Math.max(0, y1 - turm.getRange()); y < Math.min(this.getMap().length, y1 + turm.getRange() + 1); y++) {

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

    public ArrayList<FBS_Projektil_Interface> getProjektillist() {
        return projektillist;
    }

    public void setProjektillist(ArrayList<FBS_Projektil_Interface> projektillist) {
        this.projektillist = projektillist;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

}
