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
    
    private float homeX;
    private float reach = 500;
    
    public Soldat(float x, float y, boolean world, Eingang eingang) {
        super(x, y, "soldat", world,2,1);
        arrived = false;
        homeX = x;
        setFriction(0.5f);
        setAccFactor(0.03f + (int)Math.random()*0.5f);
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
        if(getX() < homeX - reach/2 ||
           getX() > homeX + reach/2){
           setAcceleration(getAcceleration()*(-1) );
           setX(getX() + getAcceleration()*5);
        }else if(GameScreen.getPlayer().getX() > homeX - reach/2 && // wenn player in Heimat eindringt
                 GameScreen.getPlayer().getX() < homeX + reach/2){
                if(getX() > GameScreen.getPlayer().getX()) setAcceleration(-1);
                else setAcceleration(1);
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
