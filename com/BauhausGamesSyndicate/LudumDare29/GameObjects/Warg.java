package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Warg extends Minion {

    public Warg(boolean world) {
        super(
            3141f + (float)Math.ceil((Math.random()*200)-100),
            0,
            "warg",
            world,
            3,
            3
        );
        setAccFactor(Tuning.WARG_ACCELERATION_FACTOR);
        setFriction(Tuning.WARG_FRICTION);
        setLife(Tuning.WARG_LIFE);
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        playSpecial(true);
        enemy.drainLife(delta/4);
    }
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
        
        setX(3141);
        setAcceleration(-1);
    }
    
    @Override
    public void onDeath() {
        //nothing
    }
}
