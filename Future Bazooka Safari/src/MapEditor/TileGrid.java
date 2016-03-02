/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditor;

import FBS_Interfaces.FBS_MapInterface;
import Frontend.FBS_Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Nico
 */
public class TileGrid extends FBS_Canvas {
    
static int[][] tilemap;
static int rows, columns;

    public TileGrid(FBS_MapInterface map) {
        super(map);
    }
   
    public void init() {
        
    }
    
    //Raster aufbauen.
    public void createTilemap() {
        tilemap = new int[100][100];
        rows = tilemap.length;
        columns = tilemap[1].length;
        
        for(int i=0;i<=tilemap.length;i++) {
            for(int j=0;j<=tilemap.length;j++) {
                
            }
        }
    }
    
    public void drawTilemap(Graphics g) {
        for(int i=0;i<=tilemap.length;i++) {
            for(int j=0;j<=tilemap.length;j++) {
                
                
                switch (tilemap[i][j]) {
                    case 0:
                        g.setColor(Color.WHITE);
                        g.fillRect(i, j, 1, 1);
                        break;
                    case 1:
                        g.setColor(Color.BLACK);
                        g.fillRect(i, j, i, i);
                        break;
                }
            }
        }
    }
    
}
