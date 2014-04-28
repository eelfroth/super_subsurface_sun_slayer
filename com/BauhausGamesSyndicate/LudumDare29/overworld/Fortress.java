/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;

/**
 *
 * @author Paul
 */
public class Fortress extends AbstractEntity {
    private float timetillspawn = 1000;
    private float timer;
    private final Overworld overworld;
    
    public Fortress(Overworld overworld, int x, int y) {
        super(x, y, "palisaden", true, 1,1);  

        if((int)(Math.random()*10) > 5){
            this.setFlipHorizontal(true);
        }
        this.overworld = overworld;
    }
    
    @Override
    public void update(float delta){
        timer+=delta;
        if (timer>=timetillspawn) {
            timer = 0;
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
       /*
          100% Bauern 
          Quantity: 1
       */
       Bauer enemy = new Bauer(getX(),getY(), onOverworld());

       overworld.addEntity(enemy);
    }
}
