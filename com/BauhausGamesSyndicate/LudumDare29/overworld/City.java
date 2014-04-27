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
    private final Eingang eingang;
    public City(Overworld overworld, int x, int y, Eingang eingang) {
        super(x, y, "bauernhof_h", true);
<<<<<<< HEAD
        this.eingang = eingang;
        if(0 + (int)(Math.random()*10) >= 5){
            this.setFlip(true, false);
=======
        
        if((int)(Math.random()*10) >= 5){
            this.setFlipHorizontal(true);
>>>>>>> 33b467845572ffe5b7ddea011535d265ab4d6c88
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
       Enemy enemy = new Enemy(getX(),getY(), onOverworld(), eingang);
       overworld.addEntity(enemy);
    }
}
