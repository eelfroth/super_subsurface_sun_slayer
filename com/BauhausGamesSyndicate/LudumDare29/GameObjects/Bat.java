package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;

/**
 *
 * @author Benedikt Vogler
 */
public class Bat extends Minion{

    public Bat() {
       super(GameScreen.onOverworld()?GameScreen.getOverworld().getEingang().getX():500,0);
    }

 
    
}
