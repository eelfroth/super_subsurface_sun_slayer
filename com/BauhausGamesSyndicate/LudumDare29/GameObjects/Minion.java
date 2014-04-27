package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;


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
    public void update(float delta){
        super.update(delta);
        boolean collide = false;
        
        //colission check
        for (AbstractEntity entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof Soldat && entity.getX()+entity.getWidth() > getX() && entity.getX() < getX()+entity.getWidth()){
                collide = true;
            }
        }
        
        if (!collide){
            setX(getX() + getAcceleration()*delta*getSpeed());//run to left
        }
    }
}