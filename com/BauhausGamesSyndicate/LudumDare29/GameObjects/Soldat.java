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
    
    private int dTimer;
    private int dTimerMax = 500;
    private float homeX;
    private float reach = 500;
    
    public Soldat(float x, float y, boolean world, Eingang eingang) {
        super(x, y, "soldat", world,2,1);
        arrived = false;
        homeX = x;
        dTimer = 0;
        setFriction(0.5f);
        //setAccFactor(0.03f + (int)Math.random()*0.5f);
        //setSpeed((float) (0.1f + Math.random()*.2f));
        this.eingang = eingang;
        if( (Math.random()*2)-1 > 0)
            setAcceleration(-1);
        else
            setAcceleration(1);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        /*
        dTimer += delta;
        if(dTimer >= dTimerMax){
            dTimer %= dTimerMax;
            if(Math.random()*20 < 5)
                setAcceleration(getAcceleration()*(-1));
        }
        */
        if(getX() < homeX - reach/2 ||
           getX() > homeX + reach){
           if(getX() > homeX)
               setAcceleration(-1);
           else
               setAcceleration(1);
        }
        if(GameScreen.getPlayer().getX() > homeX - reach/2 && // wenn player in Heimat eindringt
           GameScreen.getPlayer().getX() < homeX + reach){
           setX(getX() + getAcceleration()*3);
           if(GameScreen.getPlayer().getX() > getX())
               setAcceleration(1);
           else
               setAcceleration(-1);
           
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
