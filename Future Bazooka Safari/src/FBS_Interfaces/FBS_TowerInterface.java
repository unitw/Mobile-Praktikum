/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;

import javafx.scene.image.ImageView;

/**
 *
 * @author rw
 */
public interface FBS_TowerInterface {

    public void setPosition(int Posx, int Posy);

    public int getPositionx();

    public int getPositiony();

    public int getRange();

    public void setRange(int Range);

    public void setDamage(int Damage);

    public int getDamage();

    public void setAOE(int AOE);

    public int getAOE();

    public boolean isAOE();

    public void setisAOE(boolean isAOE);

    public void setAttackspeed(int Attackspeed);

    public int getAttackspeed();

    public int getGroesse();

    public void setGroesse(int Groesse);

    public void setUpgradestufe(int Upgradestufe);

    public int getUpgradestufe();

    public void setBaukosten(int Baukosten);

    public int getBaukosten();
    
    public void setLvl(int lvl);
    
    public int getLvl();
    
    
     public void setPicture(ImageView pic);
    public ImageView getPicture();

}
