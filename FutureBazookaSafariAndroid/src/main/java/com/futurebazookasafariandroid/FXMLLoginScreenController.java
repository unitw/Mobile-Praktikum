package com.futurebazookasafariandroid;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLLoginScreenController {

    @FXML
    private TextField fbs_username;
    @FXML
    private PasswordField fbs_passwort;
    @FXML
    private GridPane gridlogin;
    @FXML
    private Label titellogin;
    @FXML
    private Button signin;
    @FXML
    private Button login;
    
    

    @FXML
    private void LoginPressed(ActionEvent event) throws IOException, SQLException {

        try {
            if (FutureBazookaSafariAndroid.datenbank.login(fbs_username.getText(), fbs_passwort.getText())) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setScene(new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
                stage.setX(0);
                stage.setY(0);
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }

    }

    @FXML
    public void initialize() {
        String image = FXMLLoginScreenController.class.getResource("logo_nue.png").toExternalForm();
        gridlogin.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");

        
//        
        signin.setStyle("-fx-padding: 8 15 15 15;\n" +
"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
"    -fx-background-radius: 8;\n" +
"    -fx-background-color: \n" +
"        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
"        #9d4024,\n" +
"        #d86e3a,\n" +
"        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 1.1em;");
        signin.setPrefWidth(100);
        
        login.setStyle("-fx-padding: 8 15 15 15;\n" +
"    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
"    -fx-background-radius: 8;\n" +
"    -fx-background-color: \n" +
"        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
"        #9d4024,\n" +
"        #d86e3a,\n" +
"        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
"    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
"    -fx-font-weight: bold;\n" +
"    -fx-font-size: 1.1em;");
//        
        
        Text t = new Text("Future Bazooka Safari");
        t.setFill(Color.RED);
        t.setFont(Font.font(null, FontWeight.BOLD, 160));

        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(254, 235, 66, 0.3));
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);

        blend.setBottomInput(ds);

        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#f13a00"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(9);
        is.setChoke(0.8);
        blend2.setBottomInput(is);

        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);

        
        titellogin.setText(t.getText());
        titellogin.setEffect(blend);

    }

    @FXML
    private void SignUpPressed(ActionEvent event) throws IOException {

        try {
            if (FutureBazookaSafariAndroid.datenbank.register(fbs_username.getText(), fbs_passwort.getText())) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setScene(new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight()));
                stage.setX(0);
                stage.setY(0);
                stage.show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
        }

    }
}
