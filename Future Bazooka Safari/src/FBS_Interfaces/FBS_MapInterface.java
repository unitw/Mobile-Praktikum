/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;


import java.util.ArrayList;
import javafx.geometry.Point2D;


/**
 *
 * @author marvin
 */
public interface FBS_MapInterface {

    //Monster spawnpoint
    public Point2D getStartpunkt();

    public void setStartpunkt(Point2D Startpunkt);

    //Monster endpoint
    public Point2D getEndpunkt();

    public void setEndpunkt(Point2D Endpunkt);

    public void setMapsizex(double size);

    public double getMapsizex();

    public void setMapsizey(double size);

    public double getMapsizey();

    public ArrayList<FBS_HindernisInterface> getHindernislist();
    
    public void AddHindernis(FBS_HindernisInterface hindernis);
    
    public void initHindernislist();

    public void setmousepressed(Point2D p);
    

}
