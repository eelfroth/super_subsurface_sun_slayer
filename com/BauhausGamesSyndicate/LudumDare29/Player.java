/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.BauhausGamesSyndicate.LudumDare29.overworld.Chunk;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author Benedikt Vogler
 */
public class Player extends AbstractCharacter {
    private boolean shouldRaise = false;
    
    public Player(float x, float y) {
        super(x, y, "player");
    }
    
    @Override
    public void update(float delta){   
        if (Gdx.input.isKeyPressed(Keys.D)){
        
        }
        
        if (Gdx.input.isKeyPressed(Keys.A)){
        
        }
        
        if (Gdx.input.isKeyPressed(Keys.W) && !GameScreen.onOverworld()){
            shouldRaise=true;
        }
        
        if (Gdx.input.isKeyPressed(Keys.S) && GameScreen.onOverworld()){
        
        }
        
        if (shouldRaise && getY() >= Chunk.HEIGHT){
            GameScreen.switchWorld();
            shouldRaise=false;
        }
        
        if (shouldRaise){
            setY(getY()+delta/2);
            
        }
        
    }
    
}
