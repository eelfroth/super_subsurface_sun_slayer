package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Soldat extends AbstractCharacter {
    private final Eingang eingang;
    private boolean arrived;
    
    public Soldat(float x, float y, boolean world, Eingang eingang) {
        super(x, y, "soldat", world,2,1);
        arrived = false;
        setFriction(0.5f);
        setAcceleration(-1);
        //setSpeed((float) (0.1f + Math.random()*.2f));
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
        if(getX() > GameScreen.getPlayer().getX()) setAcceleration(-1);
        else setAcceleration(1);
       
        if(getX() > eingang.getX()-eingang.getWidth()/2   && getAcceleration() == 1 ||
           getX() < eingang.getX()+eingang.getWidth()*1.5 && getAcceleration() == -1){
            setCanWalk(false);
        }else{
            setCanWalk(true);
        }
              
    }
    
    @Override
    public boolean isEvil() {
        return false;
    }
    
    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //playSpacial(true);
        enemy.drainLife(delta/4);
    }
    
}
