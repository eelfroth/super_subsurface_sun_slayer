package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Enemy;
import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author Benedikt Vogler
 */
public class City extends Sprite {
    private int x;
    private int y;
    private float timetillspawn;
    private float timer;
    private Overworld overworld;

    public City(Overworld overworld) {
        this.overworld = overworld;
    }
    
    
    
    public void update(float delta){
        timer+=delta;
        if (timer>=timetillspawn) {
            timer = 0;
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
       Enemy enemy = new Enemy(x,y);
       overworld.addEntity(enemy);
    }
}
