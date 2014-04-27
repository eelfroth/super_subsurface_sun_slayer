package com.BauhausGamesSyndicate.LudumDare29.GameObjects;


/**
 *
 * @author Paul
 */
public abstract class Minion extends AbstractCharacter{
    
    public Minion(float x, float y, String name,boolean world, int steps, int specialsteps){
        super(x, y, name, world, steps, specialsteps);
        setSpeed((float) (0.1f + Math.random()*.2f));
    }

    @Override
    public boolean isEvil() {
        return true;
    }
}