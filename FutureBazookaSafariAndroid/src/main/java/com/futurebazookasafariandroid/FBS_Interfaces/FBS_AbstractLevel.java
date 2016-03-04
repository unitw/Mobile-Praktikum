/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Interfaces;

import java.util.ArrayList;

/**
 *
 * @author Marvin
 */
public class FBS_AbstractLevel implements FBS_LevelInterface {

    private ArrayList<Integer> spawntimes;
    private ArrayList<FBS_MonsterInterface> monsterlist;

    @Override
    public ArrayList getSpawntimes() {
        return spawntimes;
    }

    @Override
    public void setSpawntimes(ArrayList spawntimes) {
        this.spawntimes = spawntimes;
    }

    @Override
    public ArrayList<FBS_MonsterInterface> getMonster() {
        return this.monsterlist;
    }

    @Override
    public void setMonster(ArrayList<FBS_MonsterInterface> monsterlist) {
        this.monsterlist = monsterlist;
    }

    @Override
    public void initMonsterlist() {
        this.monsterlist = new ArrayList<FBS_MonsterInterface>();
    }

    @Override
    public void addMonster(FBS_MonsterInterface monster) {
        this.monsterlist.add(monster);
    }

}
