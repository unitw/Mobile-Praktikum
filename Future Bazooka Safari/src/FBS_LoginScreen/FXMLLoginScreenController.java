package FBS_LoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FXMLLoginScreenController {
    @FXML
    private void LoginPressed(ActionEvent event) {
        System.out.println("pressed \"Log In\"");
    }
    
    @FXML
    private void SignUpPressed(ActionEvent event){
        System.out.println("pressed \"Sign Up\"");
    }
}
