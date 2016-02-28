/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import FBS_Interfaces.FBS_MapInterface;


/**
 * place objects on map
 * @author rw
 */
public class FBS_Map {

    private FBS_MapInterface mapdata;
    public FBS_Map(FBS_MapInterface map) {
        this.mapdata = map;
        drawmap();
    }   

    public FBS_MapInterface getMapdata() {
        return mapdata;
    }

    public void setMapdata(FBS_MapInterface mapdata) {
        this.mapdata = mapdata;
    }

    private void drawmap() {
        int size = this.mapdata.getMapsize();
        for (int x = 0; x < size; x++) {
            for (int y=0; y< size; y++){
                //draw
            }
            
        }
        
    }
    
    
    
    
    
}
