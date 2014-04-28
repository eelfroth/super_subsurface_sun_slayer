package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;

/**
 *
 * @author Paul
 */
public class Ritter extends AbstractCharacter {
    private boolean arrived;
    
    private int dTimer;
    private int dTimerMax = 500;
    private float homeX;
    private float reach = 600;
    
    public Ritter(float x, float y, boolean world) {
        super(x, y, "reiter", world,4,3);

        arrived = false;
        homeX = x;
        dTimer = 0;
        setFriction(0.5f);
        setAccFactor(getAccFactor() + (float) (Math.random()*0.1));
        setAcceleration(-1);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        
        if(getX() < homeX - reach/2 ||
           getX() > homeX + reach/2){
           if(getX() > homeX)
               setAcceleration(-1);
           else
               setAcceleration(1);
        }
        if(GameScreen.getPlayer().getX() > homeX - reach/2 && // wenn player in Heimat eindringt
           GameScreen.getPlayer().getX() < homeX + reach/2){
           setX(getX() + getAcceleration()*2); // wengl durchdrehen!
           if(GameScreen.getPlayer().getX() > getX()) // und auf player zugehen
               setAcceleration(1);
           else
               setAcceleration(-1);
           
        }  
        if(getAcceleration()<-0.1f)
            setFlipHorizontal(true);
        if(getAcceleration()> 0.1f)
            setFlipHorizontal(false);
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
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
    }

    @Override
    public void onRise(){
        //nothing
    }
}