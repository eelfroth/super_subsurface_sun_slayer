package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Bat extends Minion{

    public Bat(boolean world) {
       super(
           GameScreen.onOverworld()?GameScreen.getOverworld().getEingang().getX():500,
           0,
           "fledermaus",
           world,
           4
       );
    }

 
    
}
