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
    
    
    /*---CHARACTER/ENTITY (alle Spielfiguren)---*/
    //beschleunigung:
    public final static float CHARACTER_ACCELERATION_FACTOR = 0.03f;
    public final static float CHARACTER_FRICTION = 0.05f;
    //unterwelt spawn:
    public final static float CHARACTER_UNDERWORLD_START_LOCATION_Y = 50;
    //maximale vertikale verteilung:
    public final static double CHARACTER_VERTICAL_OFFSET = 20;
    //weltenwechsel geschwindigkeiten:
    public final static float CHARACTER_RISE_SPEED = 0.5f;
    public final static float CHARACTER_DESCEND_SPEED = 0.5f;
    //leben:
    public final static float ENTITY_LIFE = 100f;
    
    /*---PLAYER---*/
    public final static float PLAYER_ACCELERATION_FACTOR = 0.03f;
    public final static float PLAYER_FRICTION = 0.05f;
    public final static float PLAYER_UNDERWORLD_START_LOCATION_Y = 550f;
    public final static float PLAYER_LIFE = 100f;
    public final static float PLAYER_DAMAGE_PER_ATTACK = 10f;
    
    /*---WARG---*/
    public final static float WARG_ACCELERATION_FACTOR = 0.03f;
    public final static float WARG_FRICTION = 0.03f;
    public final static float WARG_LIFE = 15f;
    public final static float WARG_DAMAGE_PER_ATTACK = 3f;
    
    /*---SLENDER---*/
    public final static float SLENDER_ACCELERATION_FACTOR = 0.02f;
    public final static float SLENDER_FRICTION = 0.05f;
    public final static float SLENDER_LIFE = 5f;
    
    /*---BAT---*/
    public static float BAT_ACCELERATION_FACTOR = 1f;
    public static float BAT_FRICTION = 0.5f;
    public static float BAT_LIFE = 20f;
    
    /*---KAUFMENÜ---*/
    public final static int TIME_BETWEEN_BUY = 500;
    public final static int WARG_KOSTEN = 5;
    public final static int SLENDER_KOSTEN = 1;
    public final static int BAT_KOSTEN = 10;
    
    
    /*---SPAWNING---*/
    public final static int TIME_TILL_SPAWN = 10000;
    
    // City Bauernhof
    //public final String BAUERNHOF_ = "bauernhof";
    public final static int BAUERNHOF_QUANTITY = 1;
    public final static int BAUERNHOF_SPAWN_BAUER_RATE = 1;
    public final static int BAUERNHOF_MAX_BAUERN = 10;
    
    // Siedlung
    //public final String BAUERNHOF_ = "bauernhof";
    public final static int SIEDLUNG_QUANTITY = 2;
    public final static int SIEDLUNG_SPAWN_BAUER_RATE = 1;
    public final static int SIEDLUNG_MAX_BAUERN = 23;
    
    // City2 bef Bauernhof
    //public final String BAUERNHOF2_NAME = "befestigter_bauernhof";
    public final static int BAUERNHOF2_QUANTITY = 2;
    public final static int BAUERNHOF2_SPAWN_BAUER_RATE = 2;
    public final static int BAUERNHOF2_SPAWN_LANZE_RATE = 1;
    public final static int BAUERNHOF2_MAX_BAUERN = 15;
    public final static int BAUERNHOF2_MAX_LANZEN = 5;
    
    // CASTLE Burg
    //public final String BURG_NAME = "burg";
    public final static int BURG_QUANTITY = 6;
    public final static int BURG_SPAWN_PALA_RATE   = 1;
    public final static int BURG_SPAWN_LANZE_RATE  = 2;
    public final static int BURG_SPAWN_RITTER_RATE = 2;
    public final static int BURG_MAX_LANZEN = 10;
    public final static int BURG_MAX_PALA = 5;
    public final static int BURG_MAX_RITTER = 8;
    
    // FORTRESS Palisaden
    //public final String FORTRESS_NAME = "palisaden";
    public final static int FORTRESS_QUANTITY = 4;
    public final static int FORTRESS_SPAWN_BAUER_RATE  = 2;
    public final static int FORTRESS_SPAWN_LANZE_RATE  = 3;
    public final static int FORTRESS_SPAWN_RITTER_RATE = 3;
    public final static int FORTRESS_MAX_RITTER = 4;
    public final static int FORTRESS_MAX_LANZEN = 15;
    public final static int FORTRESS_MAX_BAUERN = 30;
    
    // CASTLEOFLIGHT
    //public final String CASTLEOFLIGHT_NAME = "sonnengotttempel";
    public final static int CASTLEOFLIGHT_QUANTITY = 10; // 15
    public final static int CASTLEOFLIGHT_SPAWN_PALA_RATE   = 2; // 50
    public final static int CASTLEOFLIGHT_SPAWN_LANZE_RATE  = 3; // 30
    public final static int CASTLEOFLIGHT_SPAWN_RITTER_RATE = 2; // 20
    public final static int CASTLEOFLIGHT_MAX_RITTER = 30;
    public final static int CASTLEOFLIGHT_MAX_PALA = 20;
    public final static int CASTLEOFLIGHT_MAX_LANZEN = 40;
    
    
    
    
}
