/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rw
 */
public class FBS_AbstractMonster implements FBS_MonsterInterface {

    private int posx;
    private int posy;

    private int life;

    private int speed;

    private int loot;

    private int exp;

    private Image pic;

    private int angle;

    private int groesse;

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
    public void setSpeed(int speed) {
        this.speed = speed;

    }

    @Override
    public int getSpeed() {

        return this.speed;
    }

    @Override
    public void setLoot(int loot) {
        this.loot = loot;
    }

    @Override
    public int getLoot() {
        return this.loot;
    }

    @Override
    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public int getExp() {
        return this.exp;
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
    public void setangle(int angle) {
        this.angle = angle;

    }

    @Override
    public int getangle() {
        return this.angle;
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
