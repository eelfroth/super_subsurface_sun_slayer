/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author Benedikt Vogler
 */
public class Player extends AbstractCharacter {

    public Player(float x, float y, String name) {
        super(x, y, "player");
    }
    
    @Override
    public void update(float update){
        if (Gdx.input.isKeyPressed(Keys.D)){
        
        }
        
        if (Gdx.input.isKeyPressed(Keys.A)){
        
        }
    }
    
}
