/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.Backend;

import javafx.geometry.Point2D;

/**
 *
 * @author Marvin
 */
public class FBS_Knoten {

    private Point2D Knoten;
    private int pfadlaenge;
    private FBS_Knoten vorgaenger;

    public FBS_Knoten(int length, FBS_Knoten Vorgaenger, double posx, double posy) {

        Knoten = new Point2D(posx, posy);
        pfadlaenge = length;
        vorgaenger = Vorgaenger;

    }

    public Point2D getKnoten() {
        return Knoten;
    }

    public void setKnoten(Point2D Knoten) {
        this.Knoten = Knoten;
    }

    public FBS_Knoten getVorgaenger() {
        return vorgaenger;
    }

    public void setVorgaenger(FBS_Knoten vorgaenger) {
        this.vorgaenger = vorgaenger;
    }

    public int getPfadlaenge() {
        return pfadlaenge;
    }

    public void setPfadlaenge(int pfadlaenge) {
        this.pfadlaenge = pfadlaenge;
    }

}
