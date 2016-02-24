/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Monster;

import FBS_Interfaces.FBS_MonsterInterface;

/**
 *
 * @author rw
 */
public class FBS_Monster_Ratte implements FBS_MonsterInterface {

    private int posx;
    private int posy;

    private int life = 30;

    private int speed = 20;

    final int loot = 100;

    /**
     *
     * @param posx
     * @param posy
     */
    public FBS_Monster_Ratte(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;

    }

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
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;

    }

    @Override
    public int getSpeed() {

        return this.speed;
    }

    @Override
    public int getLoot() {
        return this.loot;
    }

}
