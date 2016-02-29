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
import java.awt.Point;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

/**
 *
 * @author rw
 */
public class FBS_Canvas extends Canvas {

    GraphicsContext gc = this.getGraphicsContext2D();
    FBS_MapInterface map;
    Image img = new Image("/resources/grassbackground.png");
    ArrayList<FBS_TowerInterface> towerlist = new ArrayList();

    public FBS_Canvas(FBS_MapInterface map) {
        super(map.getMapsize(), map.getMapsize());
        this.map = map;
        initMap();

    }

    public void initMap() {
       // gc.drawImage(img, 0, 0);

    }

    public void drawObject(Object drawable) {

        if (drawable instanceof FBS_MonsterInterface) {
            FBS_MonsterInterface mon = (FBS_MonsterInterface) drawable;
           // gc.drawImage(img, 0, 0);
            gc.setFill(Color.BEIGE);
            gc.fillRect(0, 0, 500, 500);
            
            gc.save(); // saves the current state on stack, including the current transform
               
            rotate(gc, mon.getangle(), mon.getPositionx() + mon.getGroesse() / 2, mon.getPositiony() + mon.getGroesse() / 2);
            gc.drawImage(mon.getPicture(), mon.getPositionx(), mon.getPositiony(), mon.getGroesse(), mon.getGroesse());
            gc.restore(); // back to original state (before rotation)
            for (FBS_TowerInterface tower : towerlist) {
                gc.setFill(Color.BLACK);
                gc.fillRect(tower.getPositionx()-1, tower.getPositiony()-1, tower.getGroesse()+2, tower.getGroesse()+2);
                gc.drawImage(tower.getPicture(), tower.getPositionx(), tower.getPositiony(),tower.getGroesse(),tower.getGroesse());
                
              
            }
            

//            gc.drawImage(mon.getPicture(), mon.getPositionx(), mon.getPositiony(), 64, 64);
//             gc.rotate(mon.getangle());
        }
        if (drawable instanceof FBS_TowerInterface) {
            FBS_TowerInterface tower = (FBS_TowerInterface) drawable;
            towerlist.add(tower);
           gc.drawImage(tower.getPicture(), tower.getPositionx(), tower.getPositiony(),tower.getGroesse(),tower.getGroesse());

        }
        if (drawable instanceof FBS_Projektil_Interface) {
           
            FBS_Projektil_Interface projectil = (FBS_Projektil_Interface) drawable;
            gc.drawImage(projectil.getPicture(), projectil.getPositionx(), projectil.getPositiony());

        }

    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

}
