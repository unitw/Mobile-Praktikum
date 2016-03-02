/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Interfaces;

import javafx.scene.image.Image;

/**
 *
 * @author Nico
 */
public interface FBS_HindernisInterface {
    
    public void setPosition(int Posx, int Posy);

    public int getPositionx();

    public int getPositiony();

    public void setLife(int Life);

    public int getLife();

    public void setPicture(Image pic);

    public Image getPicture();

    public int getGroesse();

    public void setGroesse(int Groesse);
}
