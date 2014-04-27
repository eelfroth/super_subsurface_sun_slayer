/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;

/**
 *
 * @author Paul
 */
public class Forrest extends AbstractEntity {
    public int x;
    public int y;
    public int life;
    private final Overworld overworld;
    public Forrest(Overworld overworld, int x, int y){
        super(x, y, "wald_h", "wald_k", true);
        if((int)(Math.random()*10) > 5){
            this.setFlipHorizontal(true);
        }
        this.overworld = overworld;
        setLife(0);
    }
    
    /*
    public void photosynthesis(){
        // make some fresh air in code 
   
    }
   */
}
