package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.AbstractSpawn;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 */
public class LightGod extends AbstractCharacter {

    
    public LightGod(float x, float y, boolean world) {
        super(x, y, "lichtgott", world, 2, 1);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        setLife(1003577);
    }
    
    @Override
    public boolean isEvil() {
        return false;
    }

    @Override
    public void fight(AbstractCharacter enemy, float delta) {

    }
    
    @Override
    public void  onDescend(){
    }

    @Override
    public void onRise(){
        //nothing
    }
    
    @Override
    public void onDeath() {
    }
}