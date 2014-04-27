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
    private boolean upLocked    = false;
    private boolean downLocked  = false;
    private boolean leftLocked  = false;
    private boolean rightLocked = false;
    
    private int menupoint = 0;
    private static Sound rising;
    
    public Player(float x, float y) {
        super(x, y, "overlord", false,10);
        rising = Gdx.audio.newSound(Gdx.files.internal("com/BauhausGamesSyndicate/LudumDare29/assets/rising.mp3"));
    }
    
    @Override
    public void update(float delta){   
        super.update(delta);
        setAcceleration(0);
        
        if (GameScreen.onOverworld()){
            if (Gdx.input.isKeyPressed(Keys.D)){
                setAcceleration(1);
                //setX(getX()+speed*delta);
            }
        
            if (Gdx.input.isKeyPressed(Keys.A)){
                setAcceleration(-1);
                //setX(getX()-speed*delta);
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
        }else {
            //move up?
            if (Gdx.input.isKeyPressed(Keys.W)&& !upLocked){
                if(menupoint == 0)
                    rise();
                else 
                    goTo(0);
                upLocked = true;
            }else{
                if(upLocked)
                    upLocked = false;
            }
            if (Gdx.input.isKeyPressed(Keys.D)&& !rightLocked){
                /*if(menupoint == 1)
                    goTo(0);
                if(menupoint == 0 || menupoint == 2)
                    goTo(3);*/
                GameScreen.getUnderworld().rotate(-1.0f);
                //rightLocked = true;
            }else{
                if(rightLocked)
                    rightLocked = false;
            }
            if (Gdx.input.isKeyPressed(Keys.S)&& !downLocked){
                goTo(2);
                downLocked = true;
            }else{
                if(downLocked)
                    downLocked = false;
            }
            if (Gdx.input.isKeyPressed(Keys.A) && !leftLocked){
                /*if(menupoint == 3)
                    goTo(0);
                if(menupoint == 0 || menupoint == 2)
                    goTo(1);*/
                GameScreen.getUnderworld().rotate(1.0f);
                //leftLocked = true;
            }else{
                if(leftLocked)
                    leftLocked = false;
            }
        }
        
        setAcceleration(getAcceleration() * getAccFactor()    );
        setVelocity    (getVelocity()     + getAcceleration() );
        setVelocity    (getVelocity()     * (1 - getFriction()) );
        setX((getX() + getVelocity()*delta));
    }
    
    private void goTo(int id){
        if (id==2){
            setX(1000);
            setY(350);
        }else if (id==1){
            setX(580);
            setY(400);
        } else if(id==3){
            setX(1400);
            setY(600);
        } else if(id==0){
            setX(860);
            setY(500);
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
}
