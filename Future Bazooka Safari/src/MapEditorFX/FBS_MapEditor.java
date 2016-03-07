/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorFX;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Nico
 */
public class FBS_MapEditor extends Application implements MouseListener {
    
    public final GridPane grid = new GridPane();
    private final int width = 640, height = 640;
    private final int columns = 10, rows = 10;
    
    @Override
    public void start(Stage stage) {
        initUI(stage);
    }
    
    private void initUI(Stage stage) {

        grid.setPrefSize(width, height);
        grid.setGridLinesVisible(true);
        
        Image imageWiese = new Image("resources/grassbackground.png", width, height, false, false);
        Image imageGras = new Image("resources/gras.png", 64, 64, false, false);
        
//        grid.add(new ImageView(imageWiese), 0, 0);
        
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++) {
                grid.add(new ImageView(imageGras), j, i);
            }
        }
        
        Scene scene = new Scene(grid);
        stage.setTitle("Editor");
        stage.setScene(scene);
        stage.show();

    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("assafa");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.println(colIndex + rowIndex);    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
