/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.Tuning;

/**
 *
 * @author Paul
 */
public class Pala extends AbstractCharacter {
    private boolean arrived;
    
    private int dTimer;
    private int dTimerMax = 500;
    private float homeX;
    private float reach = 600;
    
    public Pala(float x, float y, boolean world) {
        super(x, y, "pala", world, 4, 4);

        arrived = false;
        homeX = x;
        dTimer = 0;
        setAcceleration(-1);
        
        setAccFactor(Tuning.PALA_ACCELERATION_FACTOR + (float) (Math.random()*0.1));
        setFriction(Tuning.PALA_FRICTION);
        setLife(Tuning.PALA_LIFE);
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
           
        }else{// change direction sometimes
            dTimer += delta;
            if(dTimer >= dTimerMax){
                dTimer %= dTimerMax;
                if((int)(Math.random()*50) < 5){
                    setAcceleration(getAcceleration()*(-1));
                }
            }
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
        if(!hasDrainedLife())
            enemy.drainLife(Tuning.PALA_DAMAGE_PER_ATTACK);
    }
    
    @Override
    public void  onDescend(){
        activateWalkOnCeilingHax();
    }

    @Override
    public void onRise(){
        //nothing
    }
    
    @Override
    public void onDeath() {
        //nothing
    }
}