package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 */
public class Enemy extends AbstractCharacter {
    private final Eingang eingang;
    public Enemy(float x, float y, boolean world, Eingang eingang) {
        super(x, y, "enemy", world);
        setDirection(-1);
        setSpeed((float) (0.1f + Math.random()*.2f));
        this.eingang = eingang;
    }

    public void update(float delta) {
        /*
        if((int)(Math.random()*100) == 23){
            setDirection(-1*getDirection());
        }
        */
        if(getX() < eingang.getX()+eingang.getWidth()/2){
            setDirection(1);
        }else{
            setDirection(-1);
        }
        super.update(delta);

        boolean collide = false;
        
        //colission check
        for (AbstractEntity entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof Minion && entity.getX()+entity.getWidth() > getX() && entity.getX() < getX()+entity.getWidth()){
                collide = true;
            }
        }
        
        if (!collide){
            setX(getX() + getDirection()*delta*getSpeed());//run to left
        }
    }
    
    
    
}
