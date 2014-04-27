package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Enemy;

/**
 *
 * @author Benedikt Vogler
 */
public class City extends AbstractEntity {
    private float timetillspawn = 10000;
    private float timer;
    private final Overworld overworld;

    public City(Overworld overworld, int x, int y) {
        super(x, y, "bauernhof_h", true);
        
        if(0 + (int)(Math.random()*10) >= 5){
            this.setFlip(true, false);
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
       Enemy enemy = new Enemy(getX(),getY(), onOverworld());
       overworld.addEntity(enemy);
    }
}
