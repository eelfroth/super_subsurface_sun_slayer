package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

import com.BauhausGamesSyndicate.LudumDare29.GameScreen;
import com.BauhausGamesSyndicate.LudumDare29.overworld.AbstractSpawn;
import com.BauhausGamesSyndicate.LudumDare29.overworld.Eingang;

/**
 *
 * @author Benedikt Vogler
 */
public class LightGod extends AbstractCharacter {
    private boolean bosstot;
    public LightGod(float x, float y, boolean world) {
        super(x, y, "lichtgott", world, 2, 1);
        setLife(23000);
        bosstot = false;
    }
    
    public boolean isBossTot(){
        return bosstot;
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
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
        bosstot = true;
    }
}