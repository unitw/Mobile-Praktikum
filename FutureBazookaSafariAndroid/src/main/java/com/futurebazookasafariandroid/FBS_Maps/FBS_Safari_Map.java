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

    public FBS_Safari_Map(double mapx, double mapy) {
        this.setStartpunkt(new Point2D(0, 0));
        this.setEndpunkt(new Point2D(mapx, mapy));
        this.setMapsizex(mapx);
        this.setMapsizey(mapy);
        this.initHindernislist();
        this.AddHindernis(new FBS_Stein(290, 0));

        this.AddHindernis(new FBS_Stein(290, 45));

        this.AddHindernis(new FBS_Stein(0, 90));
        this.AddHindernis(new FBS_Stein(33, 90));
        this.AddHindernis(new FBS_Stein(66, 90));
        this.AddHindernis(new FBS_Stein(99, 90));
        this.AddHindernis(new FBS_Stein(132, 90));
        this.AddHindernis(new FBS_Stein(165, 90));
        this.AddHindernis(new FBS_Stein(290, 90));

        this.AddHindernis(new FBS_Stein(0, 135));
        this.AddHindernis(new FBS_Stein(290, 135));

        this.AddHindernis(new FBS_Stein(0, 170));
        this.AddHindernis(new FBS_Stein(290, 170));

        this.AddHindernis(new FBS_Stein(290, 205));
        this.AddHindernis(new FBS_Stein(257, 205));
        this.AddHindernis(new FBS_Stein(224, 205));
        this.AddHindernis(new FBS_Stein(191, 205));
        this.AddHindernis(new FBS_Stein(158, 205));
        this.AddHindernis(new FBS_Stein(125, 205));
        this.AddHindernis(new FBS_Stein(0, 205));

    }

}
