/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class Player extends AbstractCharacter {
    private int menupoint = 0;
    private static Sound rising;
    private static Sound growlsound;
    private static Sound stepsound;
    private float attacktimer;
    private float stepX;
    private float stepY;
    private int distanceToTravel;
    private float distanceTraveled;
    
    public Player(float x, float y) {
        super(x, y, "overlord", false,10,10);
        rising = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/rising.mp3"));
        growlsound = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/growlsingle.ogg"));
        stepsound = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/step.wav"));
    }
    
    @Override
    public void update(float delta){   
        super.update(delta);
        setAcceleration(0);
        
        if (GameScreen.onOverworld()){
            if (Gdx.input.isKeyPressed(Keys.D)){
                setAcceleration(1);
            }
        
            if (Gdx.input.isKeyPressed(Keys.A)){
                setAcceleration(-1);
            }
            
            //go down?
            Eingang eingang = GameScreen.getOverworld().getEingang();
            if (
                Gdx.input.isKeyPressed(Keys.S) &&
                getX()+getWidth()/2 > eingang.getX() &&
                getX()+getWidth()/2 < eingang.getX()+eingang.getWidth()
                ){
                descend();
            }
        }else {//underworld
            
            if(!isRaising()){
                //    setY(Gdx.graphics.getHeight()/20);
                
                
                
                if (distanceTraveled<distanceToTravel){//traveling
                    float dX = stepX*delta/4; 
                    float dY = stepY*delta/4; 
                    setX(getX()+dX);
                    setY(getY()+dY);
                    distanceTraveled += Math.sqrt(dX*dX+dY*dY);
                } else {
                    //rise?
                    if (Gdx.input.isKeyPressed(Keys.W)){
                        rise();
                    }

                    if (Gdx.input.isKeyPressed(Keys.D)){
                        goTo(3);
                    }

                    if (Gdx.input.isKeyPressed(Keys.S)){
                        goTo(2);
                    }

                    if (Gdx.input.isKeyPressed(Keys.A)){
                        goTo(1);
                    }
                
                }
                

            }
        }
        
        if (attacktimer>0) {
            attacktimer-=delta;//currently attacking
            setVelocity(0);
        } else {
            if (GameScreen.onOverworld() && Gdx.input.isKeyPressed(Keys.SPACE)){
                attack();
            } else {
                playSpacial(false);
            }
        }
        
        //walkingsound
        if (getVelocity()<0.1f && getVelocity()>-0.1f){
            stepsound.stop();
        } else {
            stepsound.loop(0.7f);
        }
    }
    
    private void goTo(int id){
        if (id==2 && menupoint==1){
            flyTo(1, 2, 300);
        }else if (id==2 && menupoint==0){
            flyTo(1, -2, 400);
        } else if(id==2 && menupoint==3){
            flyTo(1, -2, 600);
        } else if(id==1 && menupoint==0){
            flyTo(-1, 1, 300);
        } else if(id==1 && menupoint==3){
            flyTo(1, 2, 300);
        } else if(id==1 && menupoint==2){
            flyTo(-2, 1, 500);
        } else if(id==3 && menupoint==1){
            flyTo(1, 0.3f, 500);
        } else if(id==3 && menupoint==0){
            flyTo(1, 2, 300);
        } else if(id==3 && menupoint==2){
            flyTo(1, 2, 300);
        }
        
        
        menupoint = id;
    }

    public int getMenupoint() {
        return menupoint;
    } 

    @Override
    public void rise() {
        super.rise();
        rising.play();
    }
    
    public void dispose(){
        rising.dispose();
    }
    
    /**
     * was soll passieren, wenn die SPielfigur angreift?
     */
    public void attack(){
        playSpacial(true);
        attacktimer = 2000;
        stepsound.stop();
        growlsound.play();
        
    }
    
    @Override
    public boolean isEvil() {
        return true;
    }

    @Override
    public void fight(AbstractCharacter enemy, float delta) {
        //player does nothing
    }
    
    private void flyTo(float x, float y, int distance){
        this.stepX = x;
        this.stepY = y;
        this.distanceToTravel = distance;
        this.distanceTraveled=0;
    }
    
    
}
