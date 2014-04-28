package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Slender extends Minion {

    public Slender(boolean world) {
        super(
            3141f + (float)Math.ceil((Math.random()*200)-100),
            0,
            "slender",
            world,
            7,
            1
        );
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //slender does nothing
    }
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
    }
}
