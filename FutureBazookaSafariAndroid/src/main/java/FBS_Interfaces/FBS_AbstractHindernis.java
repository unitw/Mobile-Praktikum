/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;

import javafx.scene.image.Image;

/**
 *
 * @author Nico
 */
public class FBS_AbstractHindernis implements FBS_HindernisInterface {
    
    private int posx, posy, life, groesse;
    
    private Image pic;
    
    @Override
    public void setPosition(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }
    
    @Override
    public int getPositionx() {
        return this.posx;
    }
    
    @Override
    public int getPositiony() {
        return this.posy;
    }
    
    @Override
    public void setLife(int life) {
        this.life = life;
    }
    
    @Override
    public int getLife() {
        return this.life;
    }
    
    @Override
    public void setPicture(Image pic) {
        this.pic = pic;
    }

    @Override
    public Image getPicture() {
        return this.pic;
    }
    
    @Override
    public int getGroesse() {
        return this.groesse;
    }

    @Override
    public void setGroesse(int Groesse) {
        this.groesse = Groesse;
    }
}
