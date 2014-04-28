package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 */
public class Bauer extends AbstractCharacter {

    private int dTimer;
    private int dTimerMax = 500;
    private float homeX;
    private float reach = 600;
    
    public Bauer(float x, float y, boolean world) {
        super(x, y, "zivi", world, 1, 1);
        homeX = x;
        setFriction(0.5f);
        setAccFactor(getAccFactor() + (float) (Math.random()*0.1));
        setAcceleration(-1);
    }
    
        @Override
    public void update(float delta) {
        super.update(delta);
        
        if(!isDescending()) {
            /*
            if(getX() < homeX - reach/2 ||
               getX() > homeX + reach/2){
               if(getX() > homeX)
                   setAcceleration(-1);
               else
                   setAcceleration(1);
            }
            */
            if(GameScreen.getPlayer().getX() > getX() - reach/2 && // wenn player in Heimat eindringt
               GameScreen.getPlayer().getX() < getX() + reach/2){
               setX(getX() + getAcceleration()*2); // wengl durchdrehen!
               playSpecial(true);
               if(GameScreen.getPlayer().getX() > getX()) // und auf player zugehen
                   setAcceleration(-1);
               else
                   setAcceleration(1);

            }else{
                playSpecial(false);
                dTimer += delta;
                if(dTimer >= dTimerMax){
                    dTimer %= dTimerMax;
                    if((int)(Math.random()*50) < 5){
                        setAcceleration(getAcceleration()*(-1));
                    }
                }
            }
        }
        
        if(getAcceleration()<-0.1f)
            setFlipHorizontal(true);
        if(getAcceleration()> 0.1f)
            setFlipHorizontal(false);
        
        Eingang e = GameScreen.getOverworld().getEingang();
        if(2110 < getX() && 2130 > getX()) {
            descend();
        }
        
        if(isDescending()) {
            playSpecial(true);
            setAcceleration(0);
        }
    }
    
    @Override
    public boolean isEvil() {
        return false;
    }

    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //bauer macht nichts
        // von wegen der sorgt für das Essen in der Gesellschaft!
        
        //Bauer:
        //Ich mache was ich will!
        doAnything();
    }

    private void doAnything() {
        for(int i=0; i<23; i++) {
            /* SMOKE_WEED_EVERYDAY */
        }
    } 
}
