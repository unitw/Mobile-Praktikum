/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Monster;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_AbstractMonster;
import javafx.scene.image.Image;

/**
 *
 * @author rw
 */
public class FBS_SkinnyNorris extends FBS_AbstractMonster {

    private int speed=4;
    
    public FBS_SkinnyNorris(double posx, double posy) {
        this.setPosition(posx, posy);
        this.setLife(30);
        this.setSpeed(speed);
        this.setLoot(100);
        this.setPicture(new Image("skinnynorris.png"));
        this.setGroesse(64);
        this.setWaypoint(0);
    } 
}
