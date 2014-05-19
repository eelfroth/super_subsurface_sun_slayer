package com.BauhausGamesSyndicate.LudumDare29.GameObjects;

/**
 *
 * @author Benedikt Vogler
 */
public class Unicorn extends AbstractCharacter {

    
    public Unicorn(float x, float y, boolean world) {
        super(x, y, "blocker", world, 2, 1);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        setLife(1003577);
    }
    
    @Override
    public boolean isEvil() {
        return true;
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
        super.onDeath();
    }
}
