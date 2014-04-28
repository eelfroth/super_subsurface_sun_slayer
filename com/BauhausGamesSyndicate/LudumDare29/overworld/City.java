package com.BauhausGamesSyndicate.LudumDare29.overworld;

import com.BauhausGamesSyndicate.LudumDare29.GameObjects.AbstractEntity;
import com.BauhausGamesSyndicate.LudumDare29.GameObjects.Soldat;

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
    private final Eingang eingang;
    
    public City(Overworld overworld, int x, int y, Eingang eingang) {
        super(x, y, "bauernhof", true, 1,1);
        this.eingang = eingang;      

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
       Soldat enemy = new Soldat(getX(),getY(), onOverworld(), eingang);
       overworld.addEntity(enemy);
    }
}
