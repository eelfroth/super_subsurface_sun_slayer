/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractCharacter;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Overworld;


/**
 *
 * @author Paul
 */
public class Minion extends AbstractCharacter{
    
    public Minion(float x, float y){
        super(x, y, "minion");
        setSpeed(1f);
    }
    

    @Override
    public void update(float delta){
        setX(getX() + getSpeed());
        setY(Overworld.getHeightmapValue((int) getX() ));
    }

    @Override
    public void render(GameScreen gs){
        draw(gs.getBatch());
    }
}