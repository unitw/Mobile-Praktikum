/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 *
 * @author rw
 */
public interface FBS_MonsterInterface {

    public void setPosition(int Posx, int Posy);

    public int getPositionx();

    public int getPositiony();

    public void setLife(int Life);

    public int getLife();

    public void setSpeed(int Speed);

    public int getSpeed();

    public void setLoot(int loot);

    public int getLoot();

    // Die, vom Monster erhaltenen Erfahrungspunkte.
    public void setExp(int exp);

    public int getExp();

    public void setPicture(Image pic);

    public Image getPicture();

    public void setangle(int angle);

    public int getangle();

    public int getGroesse();

    public void setGroesse(int Groesse);

    public void insertzug(Point2D p);

    public boolean testZug(Point2D p);

}
