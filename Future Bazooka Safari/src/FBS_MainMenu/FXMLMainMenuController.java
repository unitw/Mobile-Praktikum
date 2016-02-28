/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FBS_MainMenu;

import FBS_Maps.FBS_Safari_Map;
import Backend.FBS_MapController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author benjamin.wolf
 */
public class FXMLMainMenuController implements Initializable {
    
    @FXML
    private void newGamePressed(ActionEvent event) {
        System.out.println("pressed \"Neues Spiel\"");
        
    }
    
    @FXML
    private void loadGamePressed(ActionEvent event) {
        System.out.println("pressed \"Spiel laden\"");
    }
    
    @FXML
    private void shopPressed(ActionEvent event) {
        System.out.println("pressed \"Shop\"");
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
