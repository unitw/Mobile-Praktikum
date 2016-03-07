package FBS_LoginScreen;

import FBS_Start.JamaicanFashionSisters;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLLoginScreenController {

    @FXML
    private TextField fbs_username;
    @FXML
    private PasswordField fbs_passwort;

    @FXML
    private void LoginPressed(ActionEvent event) throws IOException {

        try {
            if (JamaicanFashionSisters.datenbank.login(fbs_username.getText(), fbs_passwort.getText())) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/FBS_MainMenu/FXMLMainMenu.fxml"));

                stage.setScene(new Scene(root, 288, 512));
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXMLLoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void SignUpPressed(ActionEvent event) throws IOException {
        System.out.println("pressed \"Sign Up\"");

        
        
        try {
            if(JamaicanFashionSisters.datenbank.register(fbs_username.getText(), fbs_passwort.getText())){
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Parent root = FXMLLoader.load(getClass().getResource("/FBS_MainMenu/FXMLMainMenu.fxml"));
                
                stage.setScene(new Scene(root, 288, 512));
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXMLLoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
