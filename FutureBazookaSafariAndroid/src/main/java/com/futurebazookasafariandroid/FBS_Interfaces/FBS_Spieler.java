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
public class FBS_Spieler extends FBS_AbstractSpieler {

    private int rank;
    private int edelsteine;
    private int gold;
    private int life;

    public FBS_Spieler(int rank, int edelsteine, int gold, int life) {

        this.setRank(rank);
        this.setEdelsteine(edelsteine);
        this.setstartGold(gold);
        this.setmaxLife(life);

    }

    
    
    
    
    
    
    
}
