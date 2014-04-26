/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Chunk;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author Benedikt Vogler
 */
public class Player extends AbstractCharacter {
    private boolean shouldRaise = false;
    private float speed=1/8f;
    private int menupoint = 0;
    
    public Player(float x, float y) {
        super(x, y, "player");
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
                getX() > eingang.getX() &&
                getX() < eingang.getX()+eingang.getWidth()
                ){
                GameScreen.switchWorld();
                setX(860);
                setY(500);
            }
        }else {
            //move up?
            if (Gdx.input.isKeyPressed(Keys.W)){
                shouldRaise=true;
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
        
        setAcceleration(getAcceleration() * getAccFactor()    );
        setVelocity    (getVelocity()     + getAcceleration() );
        setVelocity    (getVelocity()     * (1 - getFriction()) );
        setX((getX() + getVelocity()*delta));
        

        
        //switch
        if (shouldRaise && getY() >= Chunk.HEIGHT){
            GameScreen.switchWorld();
            shouldRaise=false;
            setX(GameScreen.getOverworld().getEingang().getX()+GameScreen.getOverworld().getEingang().getWidth()/2);
        }
        
        if (shouldRaise){
            setY(getY()+delta/2);
        }
             
    }
    
    private void goTo(int id){
        if (menupoint==0 &&  id==2){
            setX(1000);
            setY(350);
        }else if (menupoint==0 && id==1){
            setX(580);
            setY(400);
        } else if(menupoint==0 && id==3){
            setX(1400);
            setY(600);
        } else if(menupoint==2 && id==0){
            setX(860);
            setY(500);
        }
        menupoint = id;
    }
    
}
