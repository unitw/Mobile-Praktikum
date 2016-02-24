
package pingurinth;
//Importe von Elementen in JavaFX
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Stellt die einzelnen Element für das Spielfeld zur Verfügung
public class Level {

    int fieldSize;  // Feldgröße in Pixel
    int wallSize;   // Wandbreite in Pixel
    int width;      // Breite des Spielfeldes in Feldern
    int height;     // Höhe des Spielfeldes in Feldern
    LevelField[][] fields; //Mehrdimensionaler array für alle Felder
    Image imagePengiun;    //png wird geladen
    ImageView penguin;      //png wird angezeigt
    double fishAnimation;   //Variable für Fischanimation
    Image imageFish;    //png wird geladen
    ImageView fish;     //png wird angezeigt
    Image imageHole;    //png wird geladen
    ImageView[] holes; //Liste von Löchern - ermöglicht mehrere Löcher in einem Level
    Image imageLogo;    //png wird geladen
    ImageView logo;     //png wird angezeigt
    
    enum DIRECTION { NONE, LEFT, RIGHT, UP, DOWN }; //Mögliche Richtungen des Pinguins
    DIRECTION penguinDirection = DIRECTION.NONE;    //Pinguin startet mit keiner Bewegung/Richtung
    
    
    //Level wird erstellt -->Daten kommen aus LevelData
    public Level(int w, int h, int _fieldSize, int _wallSize) {
        createLevelFields(w, h);
        fieldSize = _fieldSize; //Variablen der Methode werden erstellt
        wallSize = _wallSize;    
        imagePengiun = new Image("pinguin.png", fieldSize, fieldSize, true, true); //Bilder werden gesetzt
        penguin = new ImageView(imagePengiun);
        imageFish = new Image("fish.png", fieldSize, fieldSize, true, true);
        fish = new ImageView(imageFish);
        imageHole = new Image("hole.png", fieldSize, fieldSize, true, true);
    }
    
    //Methode zum erstellen des Feldrasters
    final void createLevelFields(int w, int h)
    {
        width = w; //Breite
        height = h;//Höhe
        fields = new LevelField[width][height]; //Mehrdimensionales Array bekommt die Werte für höhe und breite
       
        //Schleife prüft die Rastergröße in x Richtung
        for(int x=0; x<fields.length; x++) 
        {
            //Schleife prüft die Rastergröße in Y Richtung
            for(int y=0; y<fields[x].length; y++) 
            {
               //Ein Rasterfeld wird für jedes x und y definiert
                fields[x][y] = new LevelField();
            }
        }
    }
    
    //Methode um die Wände zu setzten
    void setWall(int x, int y, boolean left, boolean bottom)
    {
        fields[x][y].setWallLeft(left);
        fields[x][y].setWallBottom(bottom);
    }
   
    //Methode die den Pinguin setzt. Koordinaten in Feldeinheiten -->Umrechnung der Pixel in Felder
    void setPenguin(int posX, int posY)
    {
        penguin.relocate(posX*fieldSize, posY*fieldSize);
    }

    //Methode die den Fisch setzt. Koordinaten in Feldeinheiten -->Umrechnung der Pixel in Felder
    void setFish(int posX, int posY)
    {
        fish.relocate(posX*fieldSize, posY*fieldSize);
    }
    
    //Methode um string für Level auszulesen
    void setLevelData(String[] levelData)
    {
        // maximale Dimension herausfinden
        int dimY = levelData.length;    // Anzahl der Zeilen
        int dimX = 0;
        //Auslesen des Strings auf Anzahl der Zeichen -->Anzahl der Spalten ermittelt
        for(int r=0; r<levelData.length; r++)
        {
            if(dimX < levelData[r].length())
                dimX = levelData[r].length();
        }
        createLevelFields(dimX, dimY); //Levelspielfeld wird erstellt anhand der ausgelesenen Zeichen des strings
        
        // Strings durchlaufen und Wände setzen
        for(int r=0; r<levelData.length; r++)
        {
            for(int c=0; c<levelData[r].length(); c++)
            {
                //Je nachdem welches der Zeichen gelesen wird, ist hier definiert was dann passieren soll. ALso wo eine Wand gebaut werden soll
                switch(levelData[r].charAt(c))
                {
                    case ' ': //Leerzeichen = keine Wand
                        setWall(c, r, false, false);
                        break;
                    case '|': // Pipe = Wand links
                        setWall(c, r, true, false);
                        break;
                    case '_': //Unterstrich = Wand unten
                        setWall(c, r, false, true);
                        break;
                    case 'L': //L = Wand unten und Wand links
                        setWall(c, r, true, true);
                        break;
                }
            }
        }
    }
    
    //Methode die prüft ob Wände gesetzt werden sollen und diese setzt. Dafür wird Feld für Feld bearbeitet.
    void createWalls(Pane pane)
    {
        for(int x=0; x<fields.length; x++) //Abtastung in X Richtung
        {
            for(int y=0; y<fields[x].length; y++) //Abtastung in Y Richtung
            {
               //Methode hasWallLeft wird angesprochen und die Wände werden gebaut 
                if (fields[x][y].hasWallLeft()) {
                    Rectangle wallLeft = new Rectangle(wallSize, fieldSize+wallSize, Color.BLACK); //Breite, Höhe, Farbe der Wand
                    wallLeft.relocate(x*fieldSize-wallSize/2, y*fieldSize-wallSize/2); //Position der Wand
                    pane.getChildren().add(wallLeft); //Wand wird zum canvas hinzugefügt
                }
                //Siehe wall left
                if (fields[x][y].hasWallBottom()) {
                    Rectangle wallBottom = new Rectangle(fieldSize+wallSize, wallSize, Color.BLACK);
                    wallBottom.relocate(x*fieldSize-wallSize/2, y*fieldSize+fieldSize-wallSize/2);
                    pane.getChildren().add(wallBottom);
                }
            }
        }
    }

    //Spielfeldoptik wird erstellt
    void createBackground(Pane pane, Color backgroundColor)
    {
        //Abtastung in x Richtung Start bei 1 und Länge Feldlängenarry minus 1, damit um das Spielfeld ein grauer Rand ist.
        for(int x=1; x<fields.length-1; x++)
        {
            //Abtastung in y Richtung
            for(int y=1; y<fields[x].length-1; y++) 
            {
                //Erstellung eines Rechteckes in der Größe fieldSize. Farbe wird deklariert, in dem x und y addiert werden und modulo 2 gerechnet werden. Alle werte mit 0 bekommen die Farbe weiß. Die anderen Felder bekommen eine der drei in play unter color background deklarierten farben.
                Rectangle background = new Rectangle(fieldSize, fieldSize, ((x+y) % 2) == 1 ? backgroundColor : Color.WHITE);
                background.relocate(x*fieldSize, y*fieldSize);
                pane.getChildren().add(background); //Felder werden zum canvas hinzugefügt
            }
        }
    }
    
    //Methode die die Löcher setzt. Koordinaten in Feldeinheiten -->Umrechnung der Pixel in Felder
    void setHoles(int[] Xholes, int[] Yholes)
    {
        holes = null;   // alte Löcher entfernen
        
        if (Xholes != null) // hat das Level Löcher?
        {
            holes = new ImageView[Xholes.length];

            for(int i=0; i<Xholes.length; i++)
            {
                holes[i] = new ImageView(imageHole); //Löcher werden gesetzt entsprechend der Informationen aus Level Data
                holes[i].relocate(Xholes[i]*fieldSize, Yholes[i]*fieldSize);
            }
        }
    }
    
    //Alle Objekte(png) also Pinguin, Fisch, Löcher werden mit Oberfläche verbunden
    void createLevelObjects(Pane pane)
    {
        if (holes != null) //Gibt es keine Löcher werden keine Hinzugefügt
        {
            for (int i=0; i<holes.length; i++)
            {
                pane.getChildren().add(holes[i]);
            }
        }
        //Fisch und Pinguin müssen vorhanden sein
        pane.getChildren().add(penguin);
        pane.getChildren().add(fish);
    }
   
    /**
     * Fügt weitere Objekte um das Spielfeld zum Hintergrund hinzu: Levelzahl, Logo, Copyright.
     * @param pane 
     */
   
    void createExtraElements(Pane pane, int showLevel){

        Label header= new Label();
        String showLvNr;
        
        if (showLevel==0)
        {
            showLvNr="";
        }
        else
        {
            showLvNr="Level:"+showLevel;
        }
         Label levelNr= new Label(showLvNr);  
        
        
       
        
        
        int logoSmaler= 10;
        int logoSize=fieldSize-logoSmaler;
        
        imageLogo = new Image("logo_symbol_white.png", logoSize, logoSize, true, true);
        logo = new ImageView(imageLogo);
        logo.setLayoutX(640);
        logo.setLayoutY(5);
        
        
        levelNr.setLayoutX(20);
        levelNr.setLayoutY(20);
        levelNr.setStyle(
                "-fx-font: 22px \"SansSerif\";" +
                "-fx-text-fill: white;"
        );

         
     
        
        pane.getChildren().add(levelNr);
        pane.getChildren().add(logo);
 
        
      
        
        
        
    }
    

    //Methode die den Pinguin bewegt
    void movePenguin(DIRECTION direction)
    {
        penguinDirection = direction;
    }
    
    /**
     * 
     * @param timeDifference 
     * Methode für Bewegung des Pinguin und stoppen vor einer Wand
     */
    void animate(double timeDifference)
    {
        //wählt je nach Aktion keyevent aus
        switch(penguinDirection)
        {
            case NONE:
                break;
            case LEFT:
            {
                double x = penguin.getLayoutX()-fieldSize*timeDifference;
                double y = penguin.getLayoutY();
                int wallX = getWallX(DIRECTION.LEFT, (int)penguin.getLayoutX(), (int)penguin.getLayoutY());
                int xPingu= (int)x/fieldSize;//X Koordinate des Pinguin
                int yPingu= (int)y/fieldSize;//Y Koordinate des Pinguin
                System.out.println("x="+ xPingu+ " y="+yPingu); //Kontrollabfrage wo der Pinguin ist
                //hält Pinguin an Wand an
                if (x < wallX)
                {
                    x = wallX;
                    penguinDirection = DIRECTION.NONE;
                }
                penguin.relocate(x, y);
            }
            break;
            case RIGHT:
            {
                double x =penguin.getLayoutX()+fieldSize*timeDifference ;
                double y = penguin.getLayoutY();
                int wallX= getWallX(DIRECTION.RIGHT, (int)penguin.getLayoutX(), (int)penguin.getLayoutY());
                int xPingu= (int)x/fieldSize;
                int yPingu= (int)y/fieldSize;
                System.out.println("x="+ xPingu+ " y="+yPingu);
                if (x+fieldSize > wallX)
                    {                      
                        x = wallX-fieldSize; //hält den Pinguin vor der Wand an. Da es nur linke Wände gibt muss eine Feldgröße abgezogen werden
                        penguinDirection = DIRECTION.NONE;
                    }
                    penguin.relocate(x, y);
            } 
                break;
            case UP:
            {
                double x =penguin.getLayoutX() ;
                double y = penguin.getLayoutY()-fieldSize*timeDifference;
                int wallY= getWallY(DIRECTION.UP, (int)penguin.getLayoutX(), (int)penguin.getLayoutY());
                int xPingu= (int)x/fieldSize;
                int yPingu= (int)y/fieldSize;
                System.out.println("x="+ xPingu+ " y="+yPingu);
                if (y < wallY)
                    {                      
                        y = wallY;
                        penguinDirection = DIRECTION.NONE;
                    }
                    penguin.relocate(x, y);
            }               
                break;
            case DOWN:
               {
                double x =penguin.getLayoutX() ;
                double y = penguin.getLayoutY()+fieldSize*timeDifference;
                int wallY= getWallY(DIRECTION.DOWN, (int)penguin.getLayoutX(), (int)penguin.getLayoutY());
                int xPingu= (int)x/fieldSize;
                int yPingu= (int)y/fieldSize;
                System.out.println("x="+ xPingu+ " y="+yPingu);
                if (y+fieldSize > wallY)
                    {                      
                        y = wallY-fieldSize; //hält den Pinguin vor der Wand an. Da es nur untere Wände gibt muss eine Feldgröße abgezogen werden
                        penguinDirection = DIRECTION.NONE;
                    }
                    penguin.relocate(x, y);
            }
                break;
        }
        
        animateFish(timeDifference); //Animation Fisch wird aufgerufen
    }

    /**
     * Liefert die X-Koordinate der nächsten Wand.
     * 
     * @param direction 
     * @param currentX  Aktuelle X-Position in Pixelkoordinaten
     * @param currentY  Aktuelle Y-Position in Pixelkoordinaten
     * @return 
     * X-Koordinate der Wand in Pixeln
     * Schaut in welche Richtung der Pinguin gerade geht und wo es sich befindet und welche Wände deshalt abgeprüft werden müssen
     */
    int getWallX(DIRECTION direction, int currentX, int currentY)
    {
        int fieldX = currentX / fieldSize;
        int fieldY = currentY / fieldSize;
        
        switch(direction)
        {
            case LEFT: //x und y position wird mitgegeben. x wird runtergezählt bis dieses größer gleich null ist 
                for (int x=fieldX; x>=0; x--)
                {
                    if(fields[x][fieldY].hasWallLeft()) //Zugriff auf hasWallLeft -->Wenn Wand true dann Rückgabe der X Position der Wand
                        return x * fieldSize; //Feldkoordinate wird ermittelt
                }
                break;
            case RIGHT: //x und y position wird mitgegeben. x wird hochgezählt bis dieses größer gleich null ist
                for (int x=fieldX+1; x<width; x++)
                {
                    if(fields[x][fieldY].hasWallLeft())
                        return x * fieldSize;
                }
                break;
        }
        
        // Fehlerfall (falsche Argumente)
        return currentX;
    }
    
    /**
     * Liefert die Y-Koordinate der nächsten Wand.
     * 
     * @param direction 
     * @param currentX  Aktuelle X-Position in Pixelkoordinaten
     * @param currentY  Aktuelle Y-Position in Pixelkoordinaten
     * @return 
     * Y-Koordinate der Wand in Pixeln
     */
    int getWallY(DIRECTION direction, int currentX, int currentY)
    {
        int fieldX = currentX / fieldSize;
        int fieldY = currentY / fieldSize;
        
        switch(direction)
        {
            case UP: //x und y position wird mitgegeben. y wird runtergezählt bis dieses größer gleich null ist
                for (int y=fieldY-1; y>=0; y--)
                {
                    if(fields[fieldX][y].hasWallBottom())
                        return y * fieldSize + fieldSize;
                }
                break;
            case DOWN: //x und y position wird mitgegeben. y wird hochgezählt bis dieses größer gleich null ist
                for (int y=fieldY; y<height; y++)
                {
                    if(fields[fieldX][y].hasWallBottom())
                        return y * fieldSize + fieldSize;
                }
                break;
        }
        
        // Fehlerfall (falsche Argumente)
        return currentY;
    }
    
    /**
     * Liefert true, wenn der Pinguin den Fisch erreicht hat.
     */
    boolean catchedFish()
    {
        double b = penguin.getLayoutY() - fish.getLayoutY();
        double a = penguin.getLayoutX() - fish.getLayoutX();
        double c = Math.sqrt(a*a + b*b);
        return c < 3; //nicht = 0, 3 Pixel Tolleranz um Fehlerquelle zu minimieren
    }

    /**
     * Liefert true, wenn der Pinguin in ein Loch gefallen ist
     */
    boolean fallInHole()
    {
        if (holes != null)  // haben wir Löcher?
        {
            for (int i=0; i<holes.length; i++)
            {
                double b = penguin.getLayoutY() - holes[i].getLayoutY();
                double a = penguin.getLayoutX() - holes[i].getLayoutX();
                double c = Math.sqrt(a*a + b*b);

                if (c < 3)
                {
                    return true;
                }
            }
        }
        return false;
    }
    

    /**
     * Animiert den Fisch (Sinus als "leichte" Rotation)
     * 
     * @param timeDifference    Zeit seit letztem Aufruf in Millisekunden
     */
    void animateFish(double timeDifference)
    {
        if (!catchedFish()) //Wenn der Fisch nicht gefangen ist soll er animiert werden (er lebt noch)
        {
            fishAnimation += timeDifference/2;
            // Fisch wackelt um +/-4 Grad hin und her 
            fish.setRotate(0+Math.sin(fishAnimation*30)*4);
        }
    }
    
}
