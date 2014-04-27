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
        super(x, y, "soldat", world);
        arrived = false;
        setDirection(-1);
        setSpeed((float) (0.1f + Math.random()*.2f));
        this.eingang = eingang;
        if(getX() < eingang.getX()+eingang.getWidth()/2+getDirection()*eingang.getWidth()){
            setDirection(1);
        }else{
            setDirection(-1);
        }
    }

    public void update(float delta) {
        /*
        if((int)(Math.random()*100) == 23){
            setDirection(-1*getDirection());
        }
        */
        if(arrived){
            setDirection(0);
        }
        if(getX() > eingang.getX()-eingang.getWidth()/2 && getDirection() == 1 ||
           getX() < eingang.getX()+eingang.getWidth()*1.5 && getDirection() == -1)
            arrived = true;
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
