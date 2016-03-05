/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Projektile;

import FBS_Interfaces.FBS_AbstractProjektil;
import FBS_Interfaces.FBS_MonsterInterface;
import javafx.scene.image.Image;

/**
 *
 * @author rw
 */
public class FBS_PlasmaProjektil extends FBS_AbstractProjektil {
    
    public FBS_PlasmaProjektil(FBS_MonsterInterface mon, int posx, int posy,int damage,int AOE) {
        this.setTarget(mon);
        this.setPosition(posx, posy);
        this.setDamage(damage);
        this.setAOE(AOE);
        this.setGroesse(16);
        this.setPicture(new Image("/resources/laserkugel.png"));
    }

}
