/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorFX;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.application.Application;
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
    
    private final int columns = 10;
    
    @Override
    public void start(Stage stage) {
        initUI(stage);
    }
    
    private void initUI(Stage stage) {

        final GridPane grid = new GridPane();
        grid.setPrefSize(640, 640);
        grid.setGridLinesVisible(true);
        
        Image image = new Image("resources/gras.png");
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                   grid.add(new ImageView(image), j, i);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
