/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Interfaces;


import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author marvin
 */
public class FBS_AbstractMap implements FBS_MapInterface {

    //map als boolean dargestellt: true = bebaubar
    //false = nicht bebaubar
    private Object[][] map;

    private Point2D spawnpoint;
    private Point2D endpoint;
    private ArrayList hindernislist;
    private Point2D mousepoint;
    private double mapx;
    private double mapy;

    @Override
    public Point2D getStartpunkt() {
        return spawnpoint;
    }

    @Override
    public void setStartpunkt(Point2D Startpunkt) {
        this.spawnpoint = Startpunkt;
    }

    @Override
    public Point2D getEndpunkt() {
        return endpoint;
    }

    @Override
    public void setEndpunkt(Point2D Endpunkt) {
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
    public void setmousepressed(Point2D p) {
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

    @Override
    public void AddHindernis(FBS_HindernisInterface hindernis) {
        this.hindernislist.add(hindernis);
    }

    @Override
    public void initHindernislist() {
        this.hindernislist = new ArrayList();
    }

}
