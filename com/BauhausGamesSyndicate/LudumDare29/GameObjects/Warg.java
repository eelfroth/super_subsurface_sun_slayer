package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
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
    public void update(float delta) {
        super.update(delta);
        if(GameScreen.getPlayer().getStormIntoBattle())
            stormIntoBattle();
        else
            retreat();
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        playSpecial(true);
        if(!hasDrainedLife())
            enemy.drainLife(Tuning.WARG_DAMAGE_PER_ATTACK);
        setAcceleration(0);
    }
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
        
        setX(3141);
        setAcceleration(-1);
    }
    
    @Override
    public void onDeath() {
        super.onDeath();
        //nothing
    }
}
