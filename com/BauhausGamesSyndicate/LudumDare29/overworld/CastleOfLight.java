/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Lanze;

/**
 *
 * @author Paul
 */
public class CastleOfLight extends AbstractEntity {
    private float timetillspawn = 1000;
    private float timer;
    private final Overworld overworld;
    
    public CastleOfLight(Overworld overworld, int x, int y) {
        super(x, y, "sonnengottempel", true, 1,1);   
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
          50% Pala
          30% Ritter
          20% Lanze
          Quantity: 15
       */
       Lanze enemy = new Lanze(getX(),getY(), onOverworld());
       overworld.addEntity(enemy);
    }
}
