/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;

/**
 *
 * @author Paul
 */
public class Forrest extends AbstractEntity {
    public int x;
    public int y;
    private final Overworld overworld;
    public Forrest(Overworld overworld, int x, int y){
        super(x, y, "wald_h", true);
        this.overworld = overworld;
    }
    
    @Override
    public void update(float delta){
        
    }
    /*
    public void photosynthesis(){
        // make some fresh air in code 
   
    }
   */
}
