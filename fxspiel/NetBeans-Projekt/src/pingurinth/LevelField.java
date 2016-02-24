
package pingurinth;

/**
 *In dieser Klasse werden die Wände zwischen den Feldern auf true oder false gesetzt.
 * Wände zwischen Feldern werden nur durch Linke Wände und Wände unten dargestellt.
 * Soll eine Wand oben dargestellt werden muss es eine untere Wand im Feld darüber sein, etc.
 */
public class LevelField {
   
    private boolean wallLeft = false;
    private boolean wallBottom = false;

    public LevelField() {
    }
    
    void setWallLeft(boolean wall)
    {
        wallLeft = wall;
    }

    void setWallBottom(boolean wall)
    {
        wallBottom = wall;
    }
     //  Boolean Werrte, die true oder false zurück geben
    boolean hasWallLeft() 
    {
        return wallLeft;
    }

    boolean hasWallBottom() 
    {
        return wallBottom;
    }
}
