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
            3141f + (float)Math.ceil((Math.random()*200)-100),
            0,
            "warg",
            world,
            3,
            3
        );
        //setAccFactor(0.02f);
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
}
