/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rw
 */
public interface FBS_Projektil_Interface {
    
    
    public void setPosition(int Posx,int Posy);
    public int getPositionx();
    public int getPositiony();
    
   
    public void setDamage( int Damage);
    public int  getDamage();
    
    public void setAOE(int AOE);
    public int getAOE();
    
    public boolean isAOE();
    
    public void setSpeed (int Attackspeed);
    public int getSpeed();
    
    public void setTarget(FBS_MonsterInterface monster);
    public FBS_MonsterInterface getTarget();
    
    public void setPicture(Image pic);
    public Image getPicture();
    
    public void setGroesse(int groesse);
    public int getGroesse();
    
    
   
    
   
    
    
    
    
    
}
