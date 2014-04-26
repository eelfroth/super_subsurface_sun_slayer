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
    
    public Player(float x, float y) {
        super(x, y, "player");
    }
    
    @Override
    public void update(float delta){   
        super.update(delta);
        setAcceleration(0);
        if (Gdx.input.isKeyPressed(Keys.D)){
            setAcceleration(1);
            //setX(getX()+speed*delta);
        }
        
        if (Gdx.input.isKeyPressed(Keys.A)){
            setAcceleration(-1);
            //setX(getX()-speed*delta);
        }
        setAcceleration(getAcceleration() * getAccFactor()    );
        setVelocity    (getVelocity()     + getAcceleration() );
        setVelocity    (getVelocity()     * (1 - getFriction()) );
        setX((getX() + getVelocity()*delta));
        if (Gdx.input.isKeyPressed(Keys.W) && !GameScreen.onOverworld()){
            shouldRaise=true;
        }
        
        Eingang eingang = GameScreen.getOverworld().getEingang();
        if (
            Gdx.input.isKeyPressed(Keys.S) &&
            GameScreen.onOverworld()&&
            getX()>eingang.getX() &&
            getX()<eingang.getX()+eingang.getWidth()
            ){
            GameScreen.switchWorld();
            setX(860);
            setY(500);
        }
        
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
    
}
