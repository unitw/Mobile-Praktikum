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
public interface FBS_MapInterface {

    //Monster spawnpoint
    public Point getStartpunkt();

    public void setStartpunkt(Point Startpunkt);

    //Monster endpoint
    public Point getEndpunkt();

    public void setEndpunkt(Point Endpunkt);

    public void setMapsize(int size);

    public int getMapsize();

    public Object[][] getArray();

}
