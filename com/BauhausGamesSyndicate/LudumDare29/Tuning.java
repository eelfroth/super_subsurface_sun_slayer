/**

* Das tuning für alle festzulegenden Werte im Spiel.

**/

package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author yoji0462
 */
public final class Tuning {
    
    
    /*---CHARACTER (alle Spielfiguren)---*/
    //beschleunigung:
    public final static float ACCELERATION_FACTOR = 0.03f;
    public final static float FRICTION = 0.05f;
    //unterwelt spawn:
    public final static float UNDERWORLD_START_LOCATION_Y = 50;
    //maximale vertikale verteilung:
    public static double VERTICAL_OFFSET = 20;
    //weltenwechsel geschwindigkeiten
    public final static float RISE_SPEED = 0.5f;
    public final static float DESCEND_SPEED = 0.5f;
    
    /*---PLAYER---*/
    public final static float PLAYER_ACCELERATION_FACTOR = 0.03f;
    public final static float PLAYER_FRICTION = 0.05f;
    //unterwelt spawn:
    public final static float PLAYER_UNDERWORLD_START_LOCATION_Y   = 550;
    
    /*---KAUFMENÜ---*/
    public final static int TIME_BETWEEN_BUY = 500;
    public final static int WARG_KOSTEN = 0;
    public final static int SLENDER_KOSTEN = 0;
    public final static int BAT_KOSTEN = 0;
    
    
    /*---SPAWNING---*/
    
    // City Bauernhof
    //public final String BAUERNHOF_ = "bauernhof";
    public final static int BAUERNHOF_QUANTITY = 1;
    public final static int BAUERNHOF_SPAWN_BAUER_RATE = 100;
    
    // City2 bef Bauernhof
    //public final String BAUERNHOF2_NAME = "befestigter_bauernhof";
    public final static int BAUERNHOF2_QUANTITY = 2;
    public final static int BAUERNHOF2_SPAWN_BAUER_RATE = 70;
    public final static int BAUERNHOF2_SPAWN_LANZE_RATE = 30;
    
    // CASTLE Burg
    //public final String BURG_NAME = "burg";
    public final static int BURG_QUANTITY = 6;
    public final static int BURG_SPAWN_PALA_RATE   = 10;
    public final static int BURG_SPAWN_LANZE_RATE  = 40;
    public final static int BURG_SPAWN_RITTER_RATE = 50;
    
    // FORTRESS Palisaden
    //public final String FORTRESS_NAME = "palisaden";
    public final static int FORTRESS_QUANTITY = 4;
    public final static int FORTRESS_SPAWN_BAUER_RATE  = 20;
    public final static int FORTRESS_SPAWN_LANZE_RATE  = 70;
    public final static int FORTRESS_SPAWN_RITTER_RATE = 10;
    
    // CASTLEOFLIGHT
    //public final String CASTLEOFLIGHT_NAME = "sonnengotttempel";
    public final static int CASTLEOFLIGHT_QUANTITY = 15;
    public final static int CASTLEOFLIGHT_SPAWN_PALA_RATE   = 0; // 50
    public final static int CASTLEOFLIGHT_SPAWN_LANZE_RATE  = 100; // 30
    public final static int CASTLEOFLIGHT_SPAWN_RITTER_RATE = 0; // 20
    
    
}
