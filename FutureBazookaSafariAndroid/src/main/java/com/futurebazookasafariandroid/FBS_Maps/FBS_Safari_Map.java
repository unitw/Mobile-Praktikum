/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Maps;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_AbstractMap;
import java.awt.Point;

/**
 *
 * @author marvin
 */
public class FBS_Safari_Map extends FBS_AbstractMap {

    public FBS_Safari_Map(double mapx,double mapy) {
        this.setStartpunkt(new Point(0, 500));
        this.setEndpunkt(new Point(2000, 500));
        this.setMapsizex(mapx);
        this.setMapsizey(mapy);
        
    }

}
