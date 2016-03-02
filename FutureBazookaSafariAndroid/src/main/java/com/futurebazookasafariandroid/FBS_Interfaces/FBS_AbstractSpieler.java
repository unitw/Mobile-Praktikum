/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Interfaces;

/**
 *
 * @author rw
 */
public class FBS_AbstractSpieler implements FBS_SpielerInterface {

    private int rank;
    private int edelsteine;
    private int gold;
    private int life;

    @Override
    public void setRank(int Rank) {
        this.rank = Rank;
    }

    @Override
    public int getRank() {

        return this.rank;
    }

    @Override
    public void setEdelsteine(int Money) {
        this.edelsteine = Money;
    }

    @Override
    public int getEdelsteine() {
        return this.edelsteine;
    }

    @Override
    public void setmaxLife(int life) {
        this.life = life;
    }

    @Override
    public int getmaxLife() {
        return this.life;
    }

    @Override
    public void setstartGold(int startGold) {
        this.gold=startGold;
    }

    @Override
    public int getstartGold() {
        return this.gold;
    }

}
