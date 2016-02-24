
package pingurinth;
//Importe von Elementen in JavaFX
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


//main Klasse, der alles untergeordnet ist
public class Play extends Application {
    

    Pane canvas;            //Spielfenster
    Level level;            //Verweis Datei Level -->Datei Level wird addressierbar
    LevelData levelData;    //Verweis Datei LevelData -->Datei LevelData wird addressierbar
    int currentLevel = 0;   //Variable setzt Level Nr auf 0
    
    
    //Feldfarbe, variiert in jedem Dritten Level
    Color[] backgroundColors = {
       new Color(173/255.0, 216/255.0, 230/255.0, 1),
       new Color(172/255.0, 186/255.0, 229/255.0, 1),
       new Color(172/255.0, 229/255.0, 215/255.0, 1),
    };
    
    // gibt an, ob gerade gespielt wird (ansonsten Levelende)
    boolean playing = false;
    
    @Override
    public void start(Stage primaryStage) { //Klasse Start erstellt Stück für Stück das Spielfeld
        
        canvas = new Pane(); //Spieloberfläche wird erstellt
        canvas.setStyle("-fx-background-color: gray;"); //Spieloberflächenfarbe wird gesetzt
        canvas.setPrefSize(700,700); //Spieloberflächengröße wird festgesetzt
        
        levelData = new LevelData(); //LevelDaten werden aus der Klasse LevelData importiert
        
        level = new Level(0, 0, 70, 8); //Aufrufen der Methode Level aus Level.java
        startLevel(currentLevel); //aktuelles Level wird gestartet
        
        Scene scene = new Scene(canvas, canvas.getPrefWidth(), canvas.getPrefHeight());
        
        //Zuordnung der Tastenfunktion
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (playing) //Wenn gerade das Spiel geöffnet ist und kein Menü angezeigt wird, sind die Tasten freigegeben
            {
                switch(ke.getCode()) //wartet auf die entsprechende Auswahl
                {
                    case R: //R=Reset Button -->Setzt den Pinguin zurück auf das Startfeld und die Bewegung auf null
                        level.setPenguin(levelData.levels[currentLevel].Xpinguin, levelData.levels[currentLevel].Ypinguin);
                        level.movePenguin(Level.DIRECTION.NONE);
                        break;                
                    case DOWN:  
                        if(level.penguinDirection == Level.DIRECTION.NONE){ //Taste wird nur ausgeführt, wenn die Bewegung vorher null war
                            level.movePenguin(Level.DIRECTION.DOWN); //Bewegung des Pinguin bei Pfeiltaste nach unten, nach unten
                        } 
                        break;
                    case UP:
                        if(level.penguinDirection == Level.DIRECTION.NONE){ //Taste wird nur ausgeführt, wenn die Bewegung vorher null war
                            level.movePenguin(Level.DIRECTION.UP); //Bewegung des Pinguin bei Pfeiltaste nach oben, nach oben
                        }
                        break;
                    case LEFT:
                        if(level.penguinDirection == Level.DIRECTION.NONE){ //Taste wird nur ausgeführt, wenn die Bewegung vorher null war
                             level.movePenguin(Level.DIRECTION.LEFT); //Bewegung des Pinguin bei Pfeiltaste nach links, nach links
                        }                   
                        break;
                    case RIGHT:
                        if(level.penguinDirection == Level.DIRECTION.NONE){ //Taste wird nur ausgeführt, wenn die Bewegung vorher null war
                            level.movePenguin(Level.DIRECTION.RIGHT); //Bewegung des Pinguin bei Pfeiltaste nach rechts, nach rechts
                        }
                        break;
                }
                System.out.println("Key: " + ke.getCode().toString()); //Kontrollausgaben, dass der Tastendruck erkannt wird
            }
        });
        
        primaryStage.setTitle("Pingurinth"); //Fenstertitel
        primaryStage.setScene(scene); //Methode primaryStage wird mit Szene verbunden
        //primaryStage.setFullScreen(true);
        primaryStage.show();

        
        //Animation Timer wird eingefügt und ist für die Bewegung des Pinguins und des Fisches zuständig
        new AnimationTimer() {
            @Override
            public void handle(long now) {  // 'now' ist ein Zeitstempel in Nanosekunden
                long timeDiff_ns = now - lastTime; //Berechnet Zeitdifferenz
                lastTime = now;                
                if (timeDiff_ns > 0)
                {
                    double timeDiff_sec = timeDiff_ns / 1000000000.0; //Umrechnung Nanosekunden in Sekunden
                    level.animate(timeDiff_sec*3);//Geschwindigkeit des Pinguins                    
                    
                    if (playing) //wenn das Spiel läuft
                    {
                        if (level.catchedFish()) //und der Fisch gefangen wurde
                        {
                            showMenu(currentLevel+1 >= levelData.levels.length); //wird das Menü angezeigt und das aktuelles Level um eins hochgezählt
                        }
                        else if (level.fallInHole()) //Spiel läuft und Pinguin überschneidet sich mit einem Loch
                        {
                         
                            level.movePenguin(Level.DIRECTION.NONE); //Bewegung wird dann auf null gesetzt
                            level.setPenguin(levelData.levels[currentLevel].Xpinguin, levelData.levels[currentLevel].Ypinguin); //Pinguin wird auf Startfeld des Levels zurückgesetzt
                        }
                    }
                }
            }
            long lastTime;
        }.start(); //Vorgegebene Funktion aus AnimationTimer von Java, die die Animation startet
        
    }

    /**
     * Startet den angegebenen Level.
     */
    void startLevel(int index)
    {
        //Methoden werden aufgerufen und das Level wird erstellt
        canvas.getChildren().clear();
        level.setLevelData(levelData.levels[currentLevel].levelData);
        level.setPenguin(levelData.levels[currentLevel].Xpinguin, levelData.levels[currentLevel].Ypinguin);
        level.setFish(levelData.levels[currentLevel].Xfish, levelData.levels[currentLevel].Yfish);
        level.setHoles(levelData.levels[currentLevel].Xhole, levelData.levels[currentLevel].Yhole);
        level.createBackground(canvas, backgroundColors[currentLevel % backgroundColors.length]);
        level.createLevelObjects(canvas);
        level.createWalls(canvas);
        level.createExtraElements(canvas, currentLevel);
        level.movePenguin(Level.DIRECTION.NONE);
        playing = true;
        begin();
        
    }
    
    //Wenn aktuelles Level=0 soll das Start Menü angezeigt werden
    void begin (){
        if (currentLevel==0){
            showMenuStart();
        }
    }
    /**
     * Zeigt Startbildschirm: Anleitung und Startbutton.
     */
    void showMenuStart()
    {
        playing = false; //Status playing ist deaktiviert
        level.movePenguin(Level.DIRECTION.NONE); //Pinguin bewegt sich nicht
        
        Label label = new Label(""); //MenüLabel Hintergrund liegt über dem Spiel und ist halbtransparent schwarz
        label.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.7);"             
        );
        
        label.setPrefSize(700,700); //Größe des MenüLabels
        canvas.getChildren().add(label); //Label wird an das canvas(Fenster) angehängt
        
        Label label_title =new Label("PINGURINTH");  //Label für Spieltitel
        label_title.setStyle( //Formatierung des Titels
                "-fx-font: 48px \"SansSerif\";" +
                "-fx-alignment: CENTER;" +
                "-fx-text-fill: orange;"
        );
        //Größe und Lage des Labels
        label_title.setPrefSize((700/3)*2,200); 
        label_title.setLayoutX(700/6);
        label_title.setLayoutY(120);
        canvas.getChildren().add(label_title); //Label wird an das canvas(Fenster) angehängt
        
        //Anleitung des Spiel. Wird auf Startmenü im extra Label angezeigt
        String manual= " Der Pinguin hat Hunger!!! \n Steuere ihn mit den Pfeiltasten über die \n Eisfläche zum Fisch. \n Der Pinguin kann seine Richtung nur \n an einer Wand ändern. \n Viel Spaß und viel Erfolg. "
               ;
        Label label_manual =new Label(manual); //Label für Anleitung
        label_manual.setStyle( //Formatierung des Labels
                "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                "-fx-font: 22px \"SansSerif\";" +
                "-fx-alignment: CENTER;" +
                "-fx-text-fill: white;"
        );
        //Größe und Lage des Labels
        label_manual.setPrefSize((700/3)*2,200); 
        label_manual.setLayoutX(700/6);
        label_manual.setLayoutY(300);
        canvas.getChildren().add(label_manual);
        
        //Formatierung der Buttons
        String buttonStyle = 
                "-fx-background-color: rgba(0, 0, 0, 0.7);;" +
                "-fx-font: 30px \"SansSerif\";" +
                "-fx-alignment: CENTER;" +
                 "-fx-border-color: white;" +
                "-fx-border-width: 1;"+
                "-fx-text-fill: white;";
        
        //Button Play startet das Spiel
        Button buttonPlay = new Button("Spiel starten");
        buttonPlay.setStyle(buttonStyle); //Verweis auf den String für die Formatierung des Button
        buttonPlay.setLayoutX((700/2)-100); //Lage des Button
        buttonPlay.setLayoutY(700/2+700/4); 
        //Klickfunktion wird an den Button geheftet
        buttonPlay.setOnMouseClicked((MouseEvent me) -> {
            currentLevel=1; //Aktuelles Level wird auf eins gesetzt
            startLevel(currentLevel); //Aktuelles Level wird gestartet
            System.out.println(currentLevel); //Kontrollanzeige in der das aktuelle Level angezeigt wird
        });
        canvas.getChildren().add(buttonPlay);   //Button Play wird zum canvas hinzugefügt     
    }
    
    /**
     * Zeigt "Gewonnen" + Auswahl "Replay" oder "Next Level".
     * 
     * @param lastLevel     gibt an, ob das letzte Level bereits gespielt wurde
     */
    //Menüanzeige, die zwischen dem Ende eines Levels und dem Ende des Spiels unterscheidet
    void showMenu(boolean lastLevel)
    {
        playing = false; //Wenn das Menü angezeigt ist, ist der Spielstatus auf false
        level.movePenguin(Level.DIRECTION.NONE); //Pinguinrichtung wird auf null gesetzt

        String message; //Message für das Menü wird erstellt
        
        //unterscheidung zwischen normalen Leveln und dem letzten Level
        if (lastLevel)
        {
            message = "    Herzlichen Glückwunsch! \n Du hast alle Fische gefangen!!!\nDu und der Pinguin können jetzt \n  satt und zufrieden schlafen!";
        }
        else
        {
            message = "  Herzlichen Glückwunsch!!! \nDu hast den Fisch gefangen!";
        }
        
        //Label zum anzeigen der der Nachricht auf dem Menübildschirm wird angezeigt
        Label label = new Label(message);
        label.setStyle( //Formatierung des Labels
                "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                "-fx-font: 40px \"SansSerif\";" +
                "-fx-alignment: CENTER;" +
                "-fx-text-fill: white;"
        );
        label.setPrefSize(canvas.getWidth(), canvas.getHeight()); //Größe des Labels
        canvas.getChildren().add(label); //Label wird zum canvas hinzugefügt

        //Style für Buttons wird festgelegt
        String buttonStyle = 
                "-fx-background-color: rgba(0, 0, 0, 0.7);" +
                "-fx-font: 30px \"SansSerif\";" +
                "-fx-border-color: white;" +
                "-fx-border-width: 1;"+
                "-fx-alignment: CENTER;" +
                "-fx-text-fill: white;";

        //Neuer Button für Level nochmal wird erstellt, formatiert und aktiviert
        Button buttonReplay = new Button("Level noch mal"); 
        buttonReplay.setStyle(buttonStyle);
        buttonReplay.setLayoutX(90);
        buttonReplay.setLayoutY(canvas.getHeight()/2.5+canvas.getHeight()/4);
        buttonReplay.setOnMouseClicked((MouseEvent me) -> {
            startLevel(currentLevel);
        });
        canvas.getChildren().add(buttonReplay);

        // Neuer Button für Spiel nochmal von vorne  erstellt, formatiert und aktiviert. aber nur wenn wir uns im letzten Level befinden.
        if (lastLevel)
        {
            Button buttonStartAllOver = new Button("Nochmal von Vorne");
            buttonStartAllOver.setStyle(buttonStyle);
            buttonStartAllOver.setLayoutX(canvas.getWidth() - 350);
            buttonStartAllOver.setLayoutY(canvas.getHeight()/2.5+canvas.getHeight()/4);
            buttonStartAllOver.setOnMouseClicked((MouseEvent me) -> {
                currentLevel = 0;
                startLevel(currentLevel);
            });
            canvas.getChildren().add(buttonStartAllOver);
        }
        
        //Sonst wird ein neuer Button für nächstes Level erstellt, formatiert und aktiviert
        else
        {
            Button buttonNext = new Button("Nächstes Level");
            buttonNext.setStyle(buttonStyle);
            buttonNext.setLayoutX(canvas.getWidth() - 350);
            buttonNext.setLayoutY(canvas.getHeight()/2.5+canvas.getHeight()/4);
            buttonNext.setOnMouseClicked((MouseEvent me) -> {
                currentLevel++;
                startLevel(currentLevel);
            });
            canvas.getChildren().add(buttonNext);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        
}
