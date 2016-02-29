/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_Maps;

import FBS_Interfaces.FBS_AbstractMap;
import java.awt.Point;

/**
 *
 * @author marvin
 */
public class FBS_Safari_Map extends FBS_AbstractMap {

    public FBS_Safari_Map() {
        this.setStartpunkt(new Point(0, 250));
        this.setEndpunkt(new Point(500, 250));
        this.setMapsize(500);
        
    }

}
