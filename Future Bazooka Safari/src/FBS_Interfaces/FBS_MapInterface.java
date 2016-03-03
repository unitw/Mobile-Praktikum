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
public interface FBS_MapInterface {

    //Monster spawnpoint
    public Point getStartpunkt();

    public void setStartpunkt(Point Startpunkt);

    //Monster endpoint
    public Point getEndpunkt();

    public void setEndpunkt(Point Endpunkt);

    public void setMapsizex(double size);

    public double getMapsizex();

    public void setMapsizey(double size);

    public double getMapsizey();

    public ArrayList<FBS_HindernisInterface> getHindernislist();
    
    public void AddHindernis(FBS_HindernisInterface hindernis);
    
    public void initHindernislist();

    public void setmousepressed(Point p);
    

}
