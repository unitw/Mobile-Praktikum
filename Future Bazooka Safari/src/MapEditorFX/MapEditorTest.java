/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorFX;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Nico
 */
public class MapEditorTest extends Application {
    
    public final GridPane grid = new GridPane();
    private final int width = 640, height = 640;
    private final int columns = 10, rows = 10;
    
    final Image imageGras = new Image("resources/gras.png", 64, 64, false, false);
//    Image imageWiese = new Image("resources/grassbackground.png", width, height, false, false);
    
    @Override
    public void start(Stage stage) {
        initUI(stage);
    }
    
    private void initUI(Stage stage) {

        grid.setPrefSize(width, height);
        grid.setGridLinesVisible(true);

//      grid.add(new ImageView(imageWiese), 0, 0);
        
        for(int i = 0; i < columns; i++) {
            for(int j = 0; j < rows; j++) {
                
                grid.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {

                        System.out.println(event);
                    }
                });
            }
        }
    
        
        Scene scene = new Scene(grid);
        stage.setTitle("Editor");
        stage.setScene(scene);
        stage.show();

    }
    
//    public void drawTexture(Stage stage) {
//        for(int i = 0; i < columns; i++) {
//            for(int j = 0; j < rows; j++) {
//                grid.add(new ImageView(imageGras), j, i);
//                grid.getCursor();
//            }
//        }
//    }
    
    public void click(MouseEvent event, Cell cell) {
        cell.setGraphic(new ImageView(imageGras));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
