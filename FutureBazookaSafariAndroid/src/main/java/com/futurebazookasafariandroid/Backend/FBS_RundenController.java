/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.Backend;

import com.futurebazookasafariandroid.FBS_Interfaces.FBS_LevelInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MapInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_MonsterInterface;
import com.futurebazookasafariandroid.FBS_Interfaces.FBS_SpielerInterface;
import com.futurebazookasafariandroid.FBS_Level.FBS_LevelOne;
import java.util.ArrayList;

/**
 *
 * @author Marvin
 */
public class FBS_RundenController {
    
    private ArrayList<Integer> spawntimelist;    
    private ArrayList<FBS_MonsterInterface> monsterlist;
    private FBS_MapController mapcon;
    private FBS_MapInterface map;
    

    public FBS_RundenController(FBS_MapController control, FBS_SpielerInterface spieler) {
        this.mapcon = control; 
        this.map = mapcon.getMap();
        mapcon.setSpieler(spieler);
        starteRunde(new FBS_LevelOne(map.getStartpunkt().getX(), map.getStartpunkt().getY()));
    }
    public void starteRunde(FBS_LevelInterface Runde) {
        FBS_LevelInterface Level = Runde;
        this.monsterlist = Level.getMonster();
        this.spawntimelist = Level.getSpawntimes();
        mapcon.setMonsterspawnlist(this.monsterlist);
        mapcon.setSpawntimelist(spawntimelist);      
        mapcon.initTimer();
    }
    
    
}
