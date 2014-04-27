package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Warg extends Minion {

    public Warg(boolean world) {
        super(
            500,
            500,
            "warg",
            world,
            3,
            1
        );
    }
    
    @Override
    public void fight(AbstractCharacter enemy) {
        //playSpacial(true);
    }
}
