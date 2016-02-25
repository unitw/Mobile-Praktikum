/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Projektile;

import FBS_Interfaces.FBS_MonsterInterface;
import FBS_Interfaces.FBS_Projektil_Interface;

/**
 *
 * @author rw
 */
public class FBS_AbstractProjektil implements FBS_Projektil_Interface {

    private int posx;
    private int posy;
    private boolean isAOE = false;
    private int AOE;
    private int Speed;
    private int Damage;
    private FBS_MonsterInterface mon;

    @Override
    public void setPosition(int Posx, int Posy) {
        this.posx = Posx;
        this.posy = Posy;
    }

    @Override
    public int getPositionx() {
        return this.posx;
    }

    @Override
    public int getPositiony() {
        return this.posy;
    }

    @Override
    public void setDamage(int Damage) {
        this.Damage = Damage;
    }

    @Override
    public int getDamage() {
        return this.Damage;
    }

    @Override
    public void setAOE(int AOE) {
        this.AOE = AOE;
        if (AOE == 0) {
            isAOE = false;
        }

    }

    @Override
    public int getAOE() {
        return this.AOE;
    }

    @Override
    public boolean isAOE() {
        return this.isAOE;
    }

    @Override
    public void setSpeed(int Attackspeed) {
        this.Speed = Attackspeed;

    }

    @Override
    public int getSpeed() {
        return this.Speed;
    }

    @Override
    public void setTarget(FBS_MonsterInterface monster) {
        this.mon = monster;
    }

    @Override
    public FBS_MonsterInterface getTarget() {
        return this.mon;

    }

}
