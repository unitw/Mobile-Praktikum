/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Maps;

import com.futurebazookasafariandroid.FBS_Hindernis.FBS_Stein;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_AbstractMap;
import javafx.geometry.Point2D;


/**
 *
 * @author marvin
 */
public class FBS_Safari_Map extends FBS_AbstractMap {

    public FBS_Safari_Map(double mapx,double mapy) {
        this.setStartpunkt(new Point2D(0, 0));
        this.setEndpunkt(new Point2D(300, 200));
        this.setMapsizex(mapx);
        this.setMapsizey(mapy);
        this.initHindernislist();
        this.AddHindernis(new FBS_Stein(200,200));
        
    }

}
