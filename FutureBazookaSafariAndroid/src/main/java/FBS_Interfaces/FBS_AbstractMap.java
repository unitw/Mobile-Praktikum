/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author marvin
 */
public class FBS_AbstractMap implements FBS_MapInterface {

    //map als boolean dargestellt: true = bebaubar
    //false = nicht bebaubar
    private Object[][] map;

    private Point spawnpoint;
    private Point endpoint;
    private ArrayList hindernislist;
    private Point mousepoint;
    private double mapx;
    private double mapy;

    @Override
    public Point getStartpunkt() {
        return spawnpoint;
    }

    @Override
    public void setStartpunkt(Point Startpunkt) {
        this.spawnpoint = Startpunkt;
    }

    @Override
    public Point getEndpunkt() {
        return endpoint;
    }

    @Override
    public void setEndpunkt(Point Endpunkt) {
        this.endpoint = Endpunkt;
    }

    @Override
    public void setMapsizex(double mapsize) {

        this.mapx = mapsize;
    }

    @Override
    public double getMapsizex() {
        return this.mapx;

    }

    @Override
    public ArrayList getHindernislist() {
        return this.hindernislist;
    }

    @Override
    public void setmousepressed(Point p) {
        this.mousepoint = p;
    }

    @Override
    public void setMapsizey(double size) {
        this.mapy = size;
    }

    @Override
    public double getMapsizey() {
        return mapy;
    }

}
