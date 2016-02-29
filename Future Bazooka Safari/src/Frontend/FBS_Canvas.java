/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import FBS_Interfaces.FBS_MapInterface;
import FBS_Interfaces.FBS_MonsterInterface;
import FBS_Interfaces.FBS_Projektil_Interface;
import FBS_Interfaces.FBS_TowerInterface;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author rw
 */
public class FBS_Canvas extends Canvas {

    GraphicsContext gc = this.getGraphicsContext2D();
    FBS_MapInterface map;
    Image img= new Image("/resources/grassbackground.png");
    

    public FBS_Canvas(FBS_MapInterface map) {
        super(map.getMapsize(), map.getMapsize());
        this.map = map;
        initMap();
    }

    public void initMap() {
        gc.drawImage(img, 0, 0);
    }

    public void drawObject(Object drawable) {

        if (drawable instanceof FBS_MonsterInterface) {
            FBS_MonsterInterface mon = (FBS_MonsterInterface) drawable;

            gc.drawImage(mon.getPicture(), mon.getPositionx(), mon.getPositiony());

        }
        if (drawable instanceof FBS_TowerInterface) {
            FBS_TowerInterface tower = (FBS_TowerInterface) drawable;
            gc.drawImage(tower.getPicture(), tower.getPositionx(), tower.getPositiony());

        }
        if (drawable instanceof FBS_Projektil_Interface) {
            FBS_Projektil_Interface projectil = (FBS_Projektil_Interface) drawable;
            gc.drawImage(projectil.getPicture(), projectil.getPositionx(), projectil.getPositiony());

        }

    }

}
