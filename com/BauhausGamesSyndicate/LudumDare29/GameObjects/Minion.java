/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractCharacter;


/**
 *
 * @author Paul
 */
public class Minion extends AbstractCharacter{
    
    public Minion(float x){
        super(x, 0, "minion");
        setSpeed(1f);
    }
    

    @Override
    public void update(float delta){
        super.update(delta);
        setX(getX() + delta*getSpeed());
    }
}