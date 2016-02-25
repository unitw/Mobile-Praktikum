/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Monster;

import FBS_Interfaces.FBS_AbstractMonster;

/**
 *
 * @author rw
 */
public class FBS_Monster_Ratte extends FBS_AbstractMonster {

    public FBS_Monster_Ratte(int posx, int posy) {
        this.setPosition(posx, posy);
        this.setLife(30);
        this.setSpeed(20);
        this.setLoot(100);
    }

    
    
    
    
}
