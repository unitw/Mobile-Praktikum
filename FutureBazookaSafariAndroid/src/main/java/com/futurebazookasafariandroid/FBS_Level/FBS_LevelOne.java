/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Level;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_AbstractLevel;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MonsterInterface;
import com.futurebazookasafariandroid.FBS_Monster.FBS_Monster_Ratte;
import static java.lang.Math.min;

import java.util.ArrayList;

/**
 *
 * @author Marvin
 */
public class FBS_LevelOne extends FBS_AbstractLevel {

    
    public FBS_LevelOne(double StartX, double StartY, int schwierigkeit) {
        this.initMonsterlist();
        ArrayList<Integer> spawnlist = new ArrayList();
        for (int i = 0; i < 10+(schwierigkeit*5); i++) {
            FBS_Monster_Ratte rat = new FBS_Monster_Ratte(StartX, StartY);
            rat.setLife(rat.getLife()+(5*schwierigkeit*rat.getLife()));
            int max = min(schwierigkeit,rat.getSpeed()-1);
            rat.setSpeed(rat.getSpeed()- max);
            rat.setLoot(rat.getLoot() + schwierigkeit*rat.getLoot());
            this.addMonster(rat);
            spawnlist.add(i*20);
        }
        this.setSpawntimes(spawnlist);
    }

}
