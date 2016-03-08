/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Tower;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_AbstractTower;
import javafx.scene.image.Image;

/**
 *
 * @author
 */
public class FBS_JustusJonas_Tower extends FBS_AbstractTower {


    
    public FBS_JustusJonas_Tower() {
        this.setisAOE(false);
        this.setAttackspeed(20);
        this.setDamage(9);
        this.setGroesse(96);
        this.setUpgradestufe(0);
        this.setRange(250);
        this.setBaukosten(100);
        this.setLvl(1);
        this.setPicture(new Image("JustusJonasTower.png"));

    }
    
    
    public FBS_JustusJonas_Tower(int posx, int posy) {
        this.setPosition(posx, posy);
        this.setisAOE(false);
        this.setAttackspeed(20);
        this.setDamage(9);
        this.setGroesse(96);
        this.setUpgradestufe(0);
        this.setRange(250);
        this.setBaukosten(100);
        this.setLvl(1);
        this.setPicture(new Image("JustusJonasTower.png"));

    }

}
