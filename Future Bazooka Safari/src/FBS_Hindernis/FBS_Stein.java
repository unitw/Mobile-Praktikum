/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Hindernis;

import FBS_Interfaces.FBS_AbstractHindernis;
import javafx.scene.image.Image;

/**
 *
 * @author Nico
 */
public class FBS_Stein extends FBS_AbstractHindernis {
    public FBS_Stein(int posx, int posy) {
        this.setPosition(posx, posy);
        this.setLife(10);
        this.setPicture(new Image("/resources/stein.png"));
        this.setGroesse(64);  
    }
}
