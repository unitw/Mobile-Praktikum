/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import FBS_Interfaces.FBS_MapInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 *
 * @author rw
 */
public class FBS_Canvas extends Canvas {

    GraphicsContext gc = this.getGraphicsContext2D();

    public FBS_Canvas(FBS_MapInterface map) {

        super(map.getMapsize(), map.getMapsize());
    }

    
    
    
    
    
    
    
    
    
}
