/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.futurebazookasafariandroid.FBS_Interfaces;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author rw
 */
public class FBS_AbstractTower implements FBS_TowerInterface {

    private int posx;
    private int posy;
    private boolean isAOE = false;
    private int AOE;
    private int Attackspeed;
    private int Damage;
    private int Groesse;
    private int range;
    private int upgradestufe;
    private int baukosten;
    private int lvl;
    private Image pic;

    private ArrayList<Integer> IntegerList = new ArrayList();

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
    public int getRange() {
        return this.range;
    }

    @Override
    public void setRange(int Range) {

        this.range = Range;
    }

    @Override
    public void setDamage(int Damage) {
        this.Damage = Damage;

    }

    @Override
    public int getDamage() {
        return this.Damage;
    }

    @Override
    public void setAOE(int AOE) {
        this.AOE = AOE;
    }

    @Override
    public int getAOE() {
        return this.AOE;
    }

    @Override
    public boolean isAOE() {
        return this.isAOE;
    }

    @Override
    public void setAttackspeed(int Attackspeed) {
        this.Attackspeed = Attackspeed;
    }

    @Override
    public int getAttackspeed() {
        return this.Attackspeed;
    }

    @Override
    public int getGroesse() {
        return this.Groesse;
    }

    @Override
    public void setGroesse(int Groesse) {
        this.Groesse = Groesse;
    }

    @Override
    public void setUpgradestufe(int Upgradestufe) {
        this.upgradestufe = Upgradestufe;
    }

    @Override

    public int getUpgradestufe() {
        return this.upgradestufe;
    }

    @Override
    public void setisAOE(boolean isAOE) {
        this.isAOE = isAOE;
    }

    @Override
    public void setBaukosten(int Baukosten) {
        this.baukosten = Baukosten;
    }

    @Override
    public int getBaukosten() {
        return this.baukosten;
    }

    @Override
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    @Override
    public int getLvl() {
        return this.lvl;
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
    public ArrayList<Integer> getIntegerList() {

        return IntegerList;
    }

    @Override
    public void addInteger(int i) {
        IntegerList.add(i);

    }

}
