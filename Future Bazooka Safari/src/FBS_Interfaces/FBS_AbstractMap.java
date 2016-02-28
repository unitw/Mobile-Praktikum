/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;

import java.awt.Point;

/**
 *
 * @author marvin
 */
public class FBS_AbstractMap implements FBS_MapInterface{

    //map als boolean dargestellt: true = bebaubar
    //false = nicht bebaubar
    private boolean[][] map;
    private Point spawnpoint;
    private Point endpoint;
    
    
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
    public boolean isbuildable(Point p){
        return this.map[(int)p.getX()][(int)p.getY()];
    }
    @Override
    public void setbuildable(Point p, boolean buildable){
        this.map[(int)p.getX()][(int)p.getY()] = buildable;
    }
    
    
    
    
    
}
