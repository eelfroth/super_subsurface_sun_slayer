package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Bauer;

/**
 *
 * @author Benedikt Vogler
 * @author Paul Flechsig
 * @author Jacob Bauer
 */
public class City extends AbstractEntity {
    private float timetillspawn = 1000;
    private float timer;
    private final Overworld overworld;
    
    public City(Overworld overworld, int x, int y) {
        super(x, y, "bauernhof", true, 1,1);  

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
