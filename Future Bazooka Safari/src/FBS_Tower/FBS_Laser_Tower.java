/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Tower;

import FBS_Interfaces.FBS_TowerInterface;

/**
 *
 * @author rw
 */
public class FBS_Laser_Tower implements FBS_TowerInterface {

    private int posx;
    private int posy;
    boolean isAOE = false;
    int AOE;
    int Attackspeed = 10;
    int Damage = 29;
    int Groesse = 16;
    int range = 150;
    int upgradestufe = 0;

    @Override
    public void setPosition(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
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
    public int getRange() {
        return this.getRange();
    }

    @Override
    public void setRange(int Range) {

        this.range = Range;
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
    public void setAttackspeed(int Attackspeed) {
        this.Attackspeed = Attackspeed;
    }

    @Override
    public int getAttackspeed() {
        return this.Attackspeed;
    }

    @Override
    public int getGroesse() {
        return this.Groesse;
    }

    @Override
    public void setGroesse(int Groesse) {
        this.Groesse = Groesse;
    }

    @Override
    public void setUpgradestufe(int Upgradestufe) {
        this.upgradestufe = Upgradestufe;
    }

    @Override

    public int getUpgradestufe() {
        return this.upgradestufe;
    }

}
