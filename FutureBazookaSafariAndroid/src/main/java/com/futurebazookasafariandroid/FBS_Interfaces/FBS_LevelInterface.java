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
public interface FBS_LevelInterface {

    public ArrayList getSpawntimes();

    public void setSpawntimes(ArrayList spwantimes);

    public ArrayList<FBS_MonsterInterface> getMonster();

    public void setMonster(ArrayList<FBS_MonsterInterface> monsterlist);
    
    public void initMonsterlist();
    
    public void addMonster(FBS_MonsterInterface monster);
}
