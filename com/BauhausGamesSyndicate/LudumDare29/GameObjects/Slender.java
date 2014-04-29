package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.Tuning;


/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Slender extends Minion {
    private boolean hasCorpse;

    public Slender(boolean world) {
        super(
            3141f + (float)Math.ceil((Math.random()*200)-100),
            0,
            "slender",
            world,
            7,
            7
        );
        setAccFactor(Tuning.SLENDER_ACCELERATION_FACTOR);
        setFriction(Tuning.SLENDER_FRICTION);
        setLife(Tuning.SLENDER_LIFE);
    }
    @Override
    public void update(float delta) {
        super.update(delta);
        
        if(hasCorpse)
            retreat();
        else 
            stormIntoBattle();
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //slender does nothing
    }
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
    }
    
    @Override
    public void onDeath() {
        //nothing
    }
}
