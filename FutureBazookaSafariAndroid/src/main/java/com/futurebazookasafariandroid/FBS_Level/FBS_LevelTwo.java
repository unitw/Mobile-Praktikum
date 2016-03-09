/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Level;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_AbstractLevel;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MonsterInterface;
import com.futurebazookasafariandroid.FBS_Monster.FBS_SkinnyNorris;
import java.util.ArrayList;

/**
 *
 * @author Marvin
 */
public class FBS_LevelTwo extends FBS_AbstractLevel {

    public FBS_LevelTwo(double StartX, double StartY) {
        this.initMonsterlist();
        ArrayList<Integer> spawnlist = new ArrayList();
        for (int i = 0; i < 10; i++) {
            this.addMonster(new FBS_SkinnyNorris(StartX, StartY));
            spawnlist.add(i*20);
        }
        this.setSpawntimes(spawnlist);
    }

}