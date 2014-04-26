package com.BauhausGamesSyndicate.LudumDare29.GameObjects;



/**
 *
 * @author Paul
 */
public class Minion extends AbstractCharacter{
    
    public Minion(float x){
        super(x, 0, "minion");
        setSpeed((float) (0.1f + Math.random()*.2f));
    }
    

    @Override
    public void update(float delta){
        super.update(delta);
        setX(getX() + delta*getSpeed());
    }
}