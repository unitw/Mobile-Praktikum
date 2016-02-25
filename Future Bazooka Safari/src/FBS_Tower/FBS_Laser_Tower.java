/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Tower;

import FBS_Interfaces.FBS_AbstractTower;

/**
 *
 * @author
 */
public class FBS_Laser_Tower extends FBS_AbstractTower {

    public FBS_Laser_Tower(int posx, int posy) {
        this.setPosition(posx, posy);
        this.setisAOE(false);
        this.setAttackspeed(10);
        this.setDamage(29);
        this.setGroesse(16);
        this.setUpgradestufe(0);
        this.setRange(100);
        this.setBaukosten(100);

    }

}
