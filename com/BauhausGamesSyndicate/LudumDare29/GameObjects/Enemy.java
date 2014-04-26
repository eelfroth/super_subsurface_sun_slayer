package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

/**
 *
 * @author Benedikt Vogler
 */
public class Enemy extends AbstractCharacter {

    public Enemy(float x, float y) {
        super(x, y, "enemy");
        setSpeed((float) (0.1f + Math.random()*.4f));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        setX(getX() - delta*getSpeed());//run to left
    }
    
    
    
}
