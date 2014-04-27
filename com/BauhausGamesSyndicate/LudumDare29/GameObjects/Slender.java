package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Slender extends Minion {

    public Slender(boolean world) {
        super(
            GameScreen.onOverworld()?GameScreen.getOverworld().getEingang().getX():500,
            0,
            "slender",
            world,
            7,
            1
        );
    }
}
