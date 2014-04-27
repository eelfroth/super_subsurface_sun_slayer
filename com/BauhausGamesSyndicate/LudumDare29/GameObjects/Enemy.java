package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Enemy extends AbstractCharacter {
    private final Eingang eingang;
    private boolean arrived;
    
    public Enemy(float x, float y, boolean world, Eingang eingang) {
        super(x, y, "soldat", world,2);
        arrived = false;
        setAcceleration(-1);
        setSpeed((float) (0.1f + Math.random()*.2f));
        this.eingang = eingang;
        if(getX() < eingang.getX()+eingang.getWidth()/2+getAcceleration()*eingang.getWidth()){
            setAcceleration(1);
        }else{
            setAcceleration(-1);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        /*
        if((int)(Math.random()*100) == 23){
            setDirection(-1*getDirection());
        }
        */
        if(arrived){
            setAcceleration(0);
        }
        if(getX() > eingang.getX()-eingang.getWidth()/2 && getAcceleration() == 1 ||
           getX() < eingang.getX()+eingang.getWidth()*1.5 && getAcceleration() == -1)
            arrived = true;

        boolean collide = false;
        
        //colission check
        for (AbstractEntity entity : GameScreen.getOverworld().getEntityList()) {
            if (entity instanceof Minion && entity.getX()+entity.getWidth() > getX() && entity.getX() < getX()+entity.getWidth()){
                collide = true;
            }
        }
        
        if (!collide){
            setX(getX() + getAcceleration()*delta*getSpeed());//run to left
        }
    }
    
    
    
}
