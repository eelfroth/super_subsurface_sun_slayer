package com.BauhausGamesSyndicate.SSSS.GameObjects;

/**
 *
 * @author Benedikt Vogler
 */
public class Leiche extends AbstractEntity {

    public Leiche(float x, float y) {
        super(x, y, "tot", true, 4, 1);
        animateOnce();
    }

    @Override
    public void onDeath() {
       
    }
    
    
}
