/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Tower;

import FBS_Interfaces.FBS_AbstractTower;
import javafx.scene.image.Image;

/**
 *
 * @author
 */
public class FBS_LaserTower extends FBS_AbstractTower {


    
    public FBS_LaserTower() {
        this.setisAOE(false);
        this.setAttackspeed(20);
        this.setDamage(8);
        this.setGroesse(64);
        this.setUpgradestufe(0);
        this.setRange(100);
        this.setBaukosten(100);
        this.setLvl(1);
        this.setPicture(new Image("/resources/Lazertower.png"));

    }
    
    
    public FBS_LaserTower(int posx, int posy) {
        this.setPosition(posx, posy);
        this.setisAOE(false);
        this.setAttackspeed(20);
        this.setDamage(8);
        this.setGroesse(64);
        this.setUpgradestufe(0);
        this.setRange(100);
        this.setBaukosten(100);
        this.setLvl(1);
        this.setPicture(new Image("/resources/Lazertower.png"));

    }

}
