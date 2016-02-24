/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingurinth;

/**
 *In dieser Klasse befinden sich die Leveldaten, die nacheinander fürs Spiel geladen werden
 */
public class LevelData {
    
    // Klasse für die Struktur der Level
    public class SingleLevel
    {
        String[] levelData; //Liste mit Leveldaten
        int Xpinguin; //X Position des Pinguins
        int Ypinguin; //Y Position des Pinguins
        int Xfish;  //X Position des Fisches
        int Yfish; //Y Position des Fisches
        //X und Y Werte für eine Liste an Löchern
        int[] Xhole; //X Position eines Loches
        int[] Yhole; //Y Position eines Loches
    }
    //Liste der Level
    SingleLevel[] levels;

    /**
     * Constructor.
     */
    LevelData()
    {
        levels = new SingleLevel[17];//initialisieren des strings mit einer spezifischen Größe
        
        int i=0;//Hilfsvariable i
        
        /*Es folgen die einzelnen Strings und benötigte Variablen für die Level
        Das Erste Level ist doppelt aufgeführt, damit es zusätlich im Hintergrung des Startmenüs zu sehen ist.*/
        
        // Level 0 5x5
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " _____ ",
            " |    |",
            " |    |",
            " |    |",
            " |    |",
            " L____|",
            "       ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=5;
        levels[i].Yfish=5;
        i++;
        
        // Level 1 5x5
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " _____ ",
            " |    |",
            " |    |",
            " |    |",
            " |    |",
            " L____|",
            "       ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=5;
        levels[i].Yfish=5;
        i++;
        
        // Level 2 5x5
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " _____ ",
            " |   ||",
            " L    |",
            " |    |",
            " |    |",
            " L____|",
            "       ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=5;
        levels[i].Yfish=5;
        i++;
        
        // Level 3 6x6
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ______ ",
            " |   | |",
            " |     |",
            " L  _  |",
            " |  _|_|",
            " |     |",
            " L__L__|",
            "      ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=4;
        levels[i].Yfish=4;
        i++;
        
        // Level 4 Herz 7x7
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " _______ ",
            " L|_L| L|",
            " |      |",
            " |      |",
            " L   | _|",
            " |L   _||",
            " | L _| |",
            " L__LL__|",
            "      ",
        };
        levels[i].Xpinguin=2;
        levels[i].Ypinguin=1;
        levels[i].Xfish=4;
        levels[i].Yfish=7;
        i++;
        
        // Level 5 8x8
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " L       |",
            " |_     ||",
            " | _   | |",
            " |    |  |",
            " |  |_   |",
            " | |  _  |",
            " ||    _ |",
            " L_______|",
            "      ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=4;
        levels[i].Yfish=5;
        i++;
        
        // Level 6 8x8
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " L|  |   |",
            " | _    ||",
            " |       |",
            " | | | _L|",
            " |_      |",
            " |     _ |",
            " | L    L|",
            " L__L___L|",
            "      ",
        };
        levels[i].Xpinguin=4;
        levels[i].Ypinguin=4;
        levels[i].Xfish=5;
        levels[i].Yfish=4;
        i++;
        
        // Level 7
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " | |   _ |",
            " | L L  L|",
            " |   _   |",
            " |  L __ |",
            " L    L  |",
            " |_  |   |",
            " | L |   |",
            " L_____L_|",
            "      ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=8;
        levels[i].Xfish=8;
        levels[i].Yfish=2;
        i++;
                        
        
        // Level 8 5x5 1.Loch
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " _____ ",
            " |   ||",
            " L    |",
            " |    |",
            " |    |",
            " L____|",
            "       ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=5;
        levels[i].Yfish=5;
        levels[i].Xhole = new int[1];
        levels[i].Yhole = new int[1];
        levels[i].Xhole[0] = 4;
        levels[i].Yhole[0] = 1;        
        i++; 
        
        // Level 9 8x8
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " | |   _ |",
            " | __ L L|",
            " |   _   |",
            " |  | __ |",
            " L    L  |",
            " |L |  __|",
            " | L | _ |",
            " L_____L_|",
            "      ",
        };
        levels[i].Xpinguin=7;
        levels[i].Ypinguin=8;
        levels[i].Xfish=2;
        levels[i].Yfish=1;
        levels[i].Xhole = new int[2];
        levels[i].Yhole = new int[2];
        levels[i].Xhole[0] = 8;
        levels[i].Yhole[0] = 1;
        levels[i].Xhole[1] = 1;
        levels[i].Yhole[1] = 8;
        i++;
        
        // Level 10 8x8
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " | L  _| |",
            " |L |_|_ |",
            " | | |_| |",
            " | _ ||_||",
            " |_ |L   |",
            " | |L  __|",
            " | ___   |",
            " LL______|",
            "      ",
        };
        levels[i].Xpinguin=8;
        levels[i].Ypinguin=8;
        levels[i].Xfish=5;
        levels[i].Yfish=5;
        levels[i].Xhole = new int[1];
        levels[i].Yhole = new int[1];
        levels[i].Xhole[0] = 3;
        levels[i].Yhole[0] = 1;        
        i++; 
        
        //Level 11 8x8
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " |     | |",
            " |__  |  |",
            " |  L  _||",
            " ||  _|  |",
            " ||_|  __|",
            " |  L   ||",
            " |      _|",
            " LL___L__|",
            "      ",
        };
        levels[i].Xpinguin=3;
        levels[i].Ypinguin=1;
        levels[i].Xfish=7;
        levels[i].Yfish=1;
        levels[i].Xhole = new int[1];
        levels[i].Yhole = new int[1];
        levels[i].Xhole[0] = 8;
        levels[i].Yhole[0] = 8;
        i++;
        
        
        // Level 12 8x8
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " || _   _|",
            " |      _|",
            " |  L    |",
            " |_   |_ |",
            " |  |_   |",
            " L |    _|",
            " |       |",
            " L_L___L_|",
            "      ",
        };
        levels[i].Xpinguin=8;
        levels[i].Ypinguin=1;
        levels[i].Xfish=8;
        levels[i].Yfish=2;
        levels[i].Xhole = new int[1];
        levels[i].Yhole = new int[1];
        levels[i].Xhole[0] = 5;
        levels[i].Yhole[0] = 4;        
        i++;

        
        //Level 13
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " |     | |",
            " |__  |  |",
            " |  L  _||",
            " ||  _|  |",
            " ||_|  __|",
            " |  L   ||",
            " |      _|",
            " LL___L__|",
            "      ",
        };
        levels[i].Xpinguin=7;
        levels[i].Ypinguin=1;
        levels[i].Xfish=1;
        levels[i].Yfish=5;
        levels[i].Xhole = new int[4];
        levels[i].Yhole = new int[4];
        levels[i].Xhole[0] = 3;
        levels[i].Yhole[0] = 4;
        levels[i].Xhole[1] = 4;
        levels[i].Yhole[1] = 3;
        levels[i].Xhole[2] = 5;
        levels[i].Yhole[2] = 2;
        levels[i].Xhole[3] = 2;
        levels[i].Yhole[3] = 5;
        i++;
        

        // Level 14
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " | |   _ |",
            " | __   L|",
            " |   _   |",
            " |  |    |",
            " L       |",
            " |L |  _ |",
            " |   |   |",
            " L_____L_|",
            "      ",
        };
        levels[i].Xpinguin=1;
        levels[i].Ypinguin=1;
        levels[i].Xfish=4;
        levels[i].Yfish=5;
        levels[i].Xhole = new int[2];
        levels[i].Yhole = new int[2];
        levels[i].Xhole[0] = 3;
        levels[i].Yhole[0] = 1;
        levels[i].Xhole[1] = 1;
        levels[i].Yhole[1] = 5;
        i++;
        
        
        // Level 15
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
            " ________ ",
            " |   |_ _|",
            " L | _  _|",
            " | _|  | |",
            " L    |  |",
            " L | _  _|",
            " |_LL | ||",
            " |    _L |",
            " L___L___|",
            "      ",
        };
        levels[i].Xpinguin=3;
        levels[i].Ypinguin=8;
        levels[i].Xfish=7;
        levels[i].Yfish=1;
        i++;
        
        
        //Level 16
        
        levels[i] = new SingleLevel();
        levels[i].levelData = new String[] {
             " ________ ",
             " | _   _ |",
             " |   _  ||",
             " | |     |",
             " |   |   |",
             " |   L   |",
             " L _  _ _|",
             " |       |",
             " L___L___|",
             "      ",
         };
         levels[i].Xpinguin=5;
         levels[i].Ypinguin=5;
         levels[i].Xfish=4;
         levels[i].Yfish=4;
         i++;

    }
}
