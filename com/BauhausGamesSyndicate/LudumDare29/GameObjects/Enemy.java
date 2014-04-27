package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

/**
 *
 * @author Benedikt Vogler
 */
public class Enemy extends AbstractCharacter {
    public int direction;
    public Enemy(float x, float y) {
        super(x, y, "enemy");
        direction = -1;
        setSpeed((float) (0.1f + Math.random()*.2f));
    }

    @Override
    public void update(float delta) {
        int randomNum = 0 + (int)(Math.random()*100);
        if(randomNum == 23){
            direction *= -1;
            if(direction == 1)
                this.setFlip(true, false);
            else
                this.setFlip(false, false);
        }
        super.update(delta);
        setX(getX() + direction*delta*getSpeed());//run to left
    }
    
    
    
}
