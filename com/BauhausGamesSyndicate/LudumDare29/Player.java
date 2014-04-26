/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.BauhausGamesSyndicate.LudumDare29.overworld.Chunk;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author Benedikt Vogler
 */
public class Player extends AbstractCharacter {
    private boolean shouldRaise = false;
    private float speed=1/2f;
    
    public Player(float x, float y) {
        super(x, y, "player");
    }
    
    @Override
    public void update(float delta){   
        if (Gdx.input.isKeyPressed(Keys.D)){
            setX(getX()+speed*delta);
        }
        
        if (Gdx.input.isKeyPressed(Keys.A)){
            setX(getX()-speed*delta);
        }
        
        if (Gdx.input.isKeyPressed(Keys.W) && !GameScreen.onOverworld()){
            shouldRaise=true;
        }
        
        if (Gdx.input.isKeyPressed(Keys.S) && GameScreen.onOverworld()){
            GameScreen.switchWorld();
        }
        
        if (shouldRaise && getY() >= Chunk.HEIGHT){
            GameScreen.switchWorld();
            shouldRaise=false;
        }
        
        if (shouldRaise){
            setY(getY()+delta/2);
        }
        
        if (GameScreen.onOverworld()){
            setY(Overworld.getHeight((int) getX()));
        }
        
    }
    
}
